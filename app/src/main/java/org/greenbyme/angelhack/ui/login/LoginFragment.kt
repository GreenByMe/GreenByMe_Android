package org.greenbyme.angelhack.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.JsonObject
import com.kakao.auth.ApiErrorCode
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.nhn.android.naverlogin.OAuthLoginHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseFragment
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.home.HomeFragment
import org.greenbyme.angelhack.ui.login.social.NaverLoginAPI
import org.greenbyme.angelhack.ui.login.social.NaverLoginUtil


class LoginFragment : BaseFragment(), GoogleApiClient.OnConnectionFailedListener {
    private val REQ_SIGN_GOOGLE = 100
    private var isLoading = false
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var sessionCallback: SessionCallback? = null

    private lateinit var googleApiClient: GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.putInt(MainActivity.ARG_STATE, MainActivity.FULLSCREEN_STATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_login.setOnClickListener {
            if (!isLoading) {
                val id = et_login_id.text.toString()
                val pw = et_login_pw.text.toString()
                val json = JsonObject()
                json.addProperty("email", id)
                json.addProperty("password", pw)
                doLogin(json)
            }
        }

        loginUsingNaver()
        loginUsingGoogle()
        loginUsingKakao()

        tv_login_sign_up.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

    }

    private fun doLogin(json: JsonObject) =
        ApiService.service.idLogin(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading = true }
            .doFinally { isLoading = false }
            .subscribe({
                when (it.status) {
                    "200" -> {
                        setToken(it.data)
                        startActivity(MainActivity.getIntent(requireContext()))
                        requireActivity().finish()
                    }
                    else -> {
                        // ERR
                    }
                }
            }, this::toastMessage)


    private fun toastMessage(t: Throwable) {
        Log.e("ACT_LOGIN", t.toString())
        Toast.makeText(context, "인터넷 연결 상태를 확인해 주세요.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginMainActivity::class.java)
        }
    }

    fun socialLogin(json: JsonObject, type: String, id: String?) =
        ApiService.service.socialLogin(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading = true }
            .doFinally { isLoading = false }
            .subscribe({
                when (it.status) {
                    "200" -> {
                        setToken(it.data)
                        startActivity(MainActivity.getIntent(requireContext()))
                        requireActivity().finish()
                    }
                    else -> {
                        // ERR
                    }
                }
            }, {
                Log.e("ERR", it.toString())
                val intent =
                    Intent(requireContext(), SocialSignupActivity::class.java)
                intent.putExtra("type", type)
                when (type) {
                    "NAVER" -> {
                        intent.putExtra("naverId", id)
                    }
                    "GOOGLE" -> {
                        intent.putExtra("googleId", id)
                    }
                    "KAKAO" -> {
                        intent.putExtra("kakaoId", id)
                    }
                }
                startActivity(intent)
                requireActivity().finish()
            })

    // 네이버 로그인 세션
    private val mOAuthLoginHandler: OAuthLoginHandler =
        @SuppressLint("HandlerLeak") object : OAuthLoginHandler() {
            @SuppressLint("CheckResult")
            override fun run(success: Boolean) {
                if (success) {
                    val accessToken: String =
                        NaverLoginUtil.getAccessToken(requireContext())
                    if (accessToken.isNotBlank()) {
                        getNaverId(accessToken)
                    }
                } else {
                    NaverLoginUtil.getError(requireContext())
                }
            }
        }

    @SuppressLint("CheckResult")
    private fun getNaverId(accessToken: String) {
        NaverLoginAPI.naverService.getUserID("Bearer $accessToken")
            .map {
                val json = JsonObject()
                json.addProperty("platformId", it.response.id)
                socialLogin(json, "NAVER", it.response.id)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, this::toastMessage)
    }

    private fun loginUsingNaver() {
        bt_login_naver.run {
            setOnClickListener {
                NaverLoginUtil.getLoginModule(requireContext())
                    .startOauthLoginActivity(requireActivity(), mOAuthLoginHandler)
            }
        }
    }


    // 구글 로그인 세션
    private fun loginUsingGoogle() {
       val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(requireContext())
            .enableAutoManage(requireActivity(), this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
            .build()

        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())

        bt_login_google.setOnClickListener {
            if (account != null) {
                val json = JsonObject()
                json.addProperty("platformId", account.id)
                socialLogin(json, "GOOGLE", account.id)
            } else {
                val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
                startActivityForResult(signInIntent, REQ_SIGN_GOOGLE)
            }
        }
    }

    private fun googleLoginResult(account: GoogleSignInAccount) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val json = JsonObject()
                    json.addProperty("id", account.id.toString())
                    socialLogin(json, "GOOGLE", account.id.toString())
                } else {
                    Log.e("ACT_LOGIN", "GOOGlE ERR")
                }
            }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Error handling")
    }

    // 카카오 로그인 세션
    private fun loginUsingKakao() {
        sessionCallback = SessionCallback()
        Session.getCurrentSession().addCallback(sessionCallback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onFailure(errorResult: ErrorResult) {
                    val result = errorResult.errorCode
                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(
                            context,
                            "네트워크 연결이 불안정합니다. 다시 시도해 주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            context,
                            "로그인 도중 오류가 발생했습니다: " + errorResult.errorMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onSessionClosed(errorResult: ErrorResult) {
                    Toast.makeText(
                        context,
                        "세션이 닫혔습니다. 다시 시도해 주세요: " + errorResult.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSuccess(result: MeV2Response) {
                    val json = JsonObject()
                    json.addProperty("platformId", result.id.toString())
                    socialLogin(json, "KAKAO", result.id.toString())
                }
            })
        }

        override fun onSessionOpenFailed(e: KakaoException) {
            Toast.makeText(
                context,
                "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onPause() {
        super.onPause()
        googleApiClient.stopAutoManage(requireActivity())
        googleApiClient.disconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //구글 로그인에서 값이 넘어온 경우
        if (requestCode == REQ_SIGN_GOOGLE) {
            val result: GoogleSignInResult? = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess == true) {
                val account: GoogleSignInAccount? = result.signInAccount
                if (account != null) {
                    googleLoginResult(account)
                }
            }
        }

        //카카오 로그인에서 값이 넘어온 경우
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
    }

}