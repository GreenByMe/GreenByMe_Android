package org.greenbyme.angelhack.ui.login

import android.content.Context
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
    var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_login.setOnClickListener {
            if (!isLoading) {
                val id = et_login_id.text.toString()
                val pw = et_login_pw.text.toString()
                val json = JsonObject()
                json.addProperty("email", id)
                json.addProperty("password", pw)
                login(json)
            }
        }


        tv_login_sign_up.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun login(json: JsonObject) =
        ApiService.service.idLogin(json)
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
                        toastMessage(it.message.toString())
                    }
                }
            }, this::toastMessage)

    private fun setToken(it: LoginDAO) {
        sharePreferences.edit {
            putString("token", it.data)
            ApiService.token = it.data
        }
    }


    private fun toastMessage(t: Throwable) {
        Log.e("ACT_LOGIN", t.toString())
        Toast.makeText(applicationContext, "인터넷 연결 상태를 확인해 주세요.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
