package org.greenbyme.angelhack.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_social_signup.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity

class SocialSignupActivity : BaseActivity() {

    private val json = JsonObject()
    var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_signup)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            title = "내가 그린"
        }

        val loginType = intent.getStringExtra("type")

        when (loginType) {
            "NAVER" -> initNaver()
            "GOOGLE" -> initGoogle()
            "KAKAO" -> initKakao()
        }

        bt_social_sign_up.setOnClickListener {
            val nickname = et_social_sign_up_nickname.text.toString()
            val email = et_social_sign_up_email.text.toString()
            val name = et_social_sign_up_name.text.toString()
            json.addProperty("platformType", loginType)
            json.addProperty("name", name)
            json.addProperty("email", email)
            json.addProperty("nickname", nickname)
            signUp(json)
        }
    }


    @SuppressLint("CheckResult")
    private fun signUp(json: JsonObject) {
        ApiService.service.socialSignUp(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val dataJson = JsonObject()
                dataJson.addProperty("platformId", it.data)
                socialLogin(json)
                toastMessage("회원가입성공")
                finish()
            }, {
                throwError(it)
                toastMessage("회원가입실패")
            })
    }

    // 카카오
    private fun initKakao() {
        val id = intent.getStringExtra("kakaoId")
        json.addProperty("platformId", id)
    }

    // 구글
    private fun initGoogle() {
        val id = intent.getStringExtra("googleId")
        json.addProperty("platformId", id)
    }

    // 네이버
    private fun initNaver() {
        val id = intent.getStringExtra("naverId")
        json.addProperty("platformId", id)
    }

    private fun setToken(it: LoginDAO) {
        sharePreferences.edit{
            putString("token", it.data)
            ApiService.token = it.data
        }
    }

    fun socialLogin(json: JsonObject) =
        ApiService.service.socialLogin(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading = true }
            .doFinally { isLoading = false }
            .subscribe({
                when (it.status) {
                    "200" -> {
                        setToken(it)
                        startActivity(MainActivity.getIntent(applicationContext))
                        finish()
                    }
                    else -> {
                        toastMessage(it.message)
                    }
                }
            }, {
                Log.e("ERR", it.toString())
            })
}