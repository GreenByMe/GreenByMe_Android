package org.greenbyme.angelhack.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.BaseActivity


class LoginMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginMainActivity::class.java)
        }
    }
}