package org.greenbyme.angelhack.ui.login

import android.content.Intent
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
        ApiService.init
        tokenLogin()
    }

    fun tokenLogin() {
        val handler = Handler()
        val disposable = ApiService.service.tokenLogin(getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handler.postDelayed(Runnable {
                    startActivity(MainActivity.getIntent(this))
                    finish()
                }, 2000)
            }, {
                startActivity(LoginActivity.getIntent(this))
                finish()
            })
    }
}