package org.greenbyme.angelhack.ui.login

import android.os.Bundle
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_signup.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity

class SignupActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        bt_sign_up.setOnClickListener {
            val id = et_sign_up_id.text.toString()
            val name = et_sign_up_name.text.toString()
            val nickname = et_sign_up_nickname.text.toString()
            val password = et_sign_up_password.text.toString()
            val passwordCheck = et_sign_up_password_check.text.toString()

            if (password == passwordCheck) {
                val json = JsonObject()
                json.addProperty("email", id)
                json.addProperty("name", name)
                json.addProperty("nickname", nickname)
                json.addProperty("password", password)


            }
        }

    }

}