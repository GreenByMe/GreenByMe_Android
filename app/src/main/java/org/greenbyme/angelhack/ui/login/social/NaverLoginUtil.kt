package org.greenbyme.angelhack.ui.login.social

import android.content.Context
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState

object NaverLoginUtil {
    private var mLoginModule: OAuthLogin? = null

    private const val CLIENT_ID = "LFs6UgZ6_rrhSDLoREwI"
    private const val CLIENT_SECRET = "YrAhfiWTg0"
    private const val CLIENT_NAME = "GreenByMe"

    fun getLoginModule(context: Context): OAuthLogin {
        return mLoginModule ?: run {
            val authLogin = OAuthLogin.getInstance()
            authLogin.init(
                context,
                CLIENT_ID,
                CLIENT_SECRET,
                CLIENT_NAME
            )
            authLogin.getAccessToken(context)

            mLoginModule = authLogin
            return@run authLogin
        }
    }

    fun getAccessToken(context: Context): String {
        return if (isLoggedIn(context)) getLoginModule(
            context
        ).getAccessToken(context) else ""
    }

    fun getRefreshToken(context: Context): String {
        return if (isLoggedIn(context)) getLoginModule(
            context
        ).getRefreshToken(context) else ""
    }

    fun isLoggedIn(context: Context): Boolean {
        return getLoginModule(context).getState(context) == OAuthLoginState.OK
    }

    fun logout(context: Context) {
        getLoginModule(context).run {
            logoutAndDeleteToken(context)
        }
    }

    fun getError(context: Context) {
        val errorCode: String = getLoginModule(context).getLastErrorCode(context).code
        val errorDesc: String = getLoginModule(context).getLastErrorDesc(context)
        Toast.makeText(
            context, "errorCode:" + errorCode
                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
        ).show()
    }
}