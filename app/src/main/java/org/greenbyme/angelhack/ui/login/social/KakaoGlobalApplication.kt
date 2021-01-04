package org.greenbyme.angelhack.ui.login.social

import android.app.Application
import com.kakao.auth.*

class KakaoGlobalApplication : Application() {
    private class KakaoSDKAdapter : KakaoAdapter() {
        // 카카오 로그인 세션을 불러올 때의 설정값을 설정하는 부분.
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf<AuthType>(AuthType.KAKAO_LOGIN_ALL)
                    //로그인을 어떤 방식으로 할지 지정
                    //KAKAO_LOGIN_ALL: 모든 로그인방식을 사용하고 싶을때 지정.
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                    // SDK 로그인시 사용되는 WebView에서 pause와 resume시에 Timer를 설정하여 CPU소모를 절약한다.
                    // true 를 리턴할경우 webview로그인을 사용하는 화면서 모든 webview에 onPause와 onResume 시에 Timer를 설정해 주어야 한다.
                    // 지정하지 않을 시 false로 설정된다.
                }

                override fun isSecureMode(): Boolean {
                    return false
                    // 로그인시 access token과 refresh token을 저장할 때의 암호화 여부를 결정한다.
                }

                override fun getApprovalType(): ApprovalType? {
                    return ApprovalType.INDIVIDUAL
                    // 일반 사용자가 아닌 Kakao와 제휴된 앱에서만 사용되는 값으로, 값을 채워주지 않을경우 ApprovalType.INDIVIDUAL 값을 사용하게 된다.
                }

                override fun isSaveFormData(): Boolean {
                    return true
                    // Kakao SDK 에서 사용되는 WebView에서 email 입력폼의 데이터를 저장할지 여부를 결정한다.
                    // true일 경우, 다음번에 다시 로그인 시 email 폼을 누르면 이전에 입력했던 이메일이 나타난다.
                }
            }
        }

        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { globalApplicationContext }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        @Volatile
        private var instance: KakaoGlobalApplication? = null
        val globalApplicationContext: KakaoGlobalApplication?
            get() {
                checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
                return instance
            }
    }
}