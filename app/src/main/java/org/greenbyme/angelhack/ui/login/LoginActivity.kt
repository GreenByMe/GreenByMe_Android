package org.greenbyme.angelhack.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.greenbyme.angelhack.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_login.setOnClickListener {
            val id = et_login_id.text.toString()
            val pw = et_login_pw.text.toString()
        }
    }
}
