package org.greenbyme.angelhack.ui.login

import android.os.Bundle
import android.os.Handler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tokenLogin()
    }

    private fun tokenLogin() {
        val handler = Handler()
        val disposable = ApiService.service.tokenLogin(getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // TODO : 깔끔하게 처리하는 방법 있을지
                when (it.status) {
                    "201" -> {
                        handler.postDelayed(Runnable {
                            ApiService.token = getToken()
                            startActivity(MainActivity.getIntent(this))
                            finish()
                        }, 2000)
                    }
                    else -> {
                        startLoginActivity()
                    }
                }

            }, {
                startLoginActivity()
            })
    }

    private fun startLoginActivity() {
        startActivity(LoginMainActivity.getIntent(this))
        finish()
    }
}