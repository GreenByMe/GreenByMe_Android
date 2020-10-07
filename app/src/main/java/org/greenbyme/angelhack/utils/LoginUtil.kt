package org.greenbyme.angelhack.utils

import android.app.Activity
import android.content.Context
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.data.OAuthLoginState

object LoginUtil {
    private var mLoginModule: OAuthLogin? = null

    private const val CLIENT_ID = "LFs6UgZ6_rrhSDLoREwI"
    private const val CLIENT_SECRET = "YrAhfiWTg0"
    private const val CLIENT_NAME = "GreenByMe"

    private fun getLoginModule(context: Context): OAuthLogin {
        return mLoginModule ?: run {
            val authLogin = OAuthLogin.getInstance()
            authLogin.init(
                context, CLIENT_ID, CLIENT_SECRET, CLIENT_NAME
            )
            authLogin.getAccessToken(context)

            mLoginModule = authLogin
            return@run authLogin
        }
    }

    fun getToken(context: Context): String {
        return if (isLoggedIn(context)) getLoginModule(context).getAccessToken(context) else ""
    }

    fun isLoggedIn(context: Context): Boolean {
        return getLoginModule(context).getState(context) == OAuthLoginState.OK
    }

    fun startLoginActivity(activity: Activity, loginHandler: OAuthLoginHandler) {
        getLoginModule(activity).startOauthLoginActivity(activity, loginHandler)
    }
}