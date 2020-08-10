package org.greenbyme.angelhack.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ApiService.init
        bt_login.setOnClickListener {
            val id = et_login_id.text.toString()
            val pw = et_login_pw.text.toString()
            val json = JsonObject()
            json.addProperty("email", id)
            json.addProperty("password", pw)
            login(json)
        }


        tv_login_sign_up.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun login(json: JsonObject) =
        ApiService.service.login(json)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sharePreferences.edit(){
                    putString("token", it)
                }

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, this::toastMessage)


    private fun toastMessage(t: Throwable) {
        Log.e("ACT_LOGIN", t.toString())
        Toast.makeText(applicationContext, "인터넷 연결 상태를 확인해 주세요.", Toast.LENGTH_SHORT).show()
    }
}
