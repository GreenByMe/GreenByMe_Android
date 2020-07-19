package org.greenbyme.angelhack.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.UserLoginDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ApiService().init
        bt_login.setOnClickListener {
            val id = et_login_id.text.toString()
            val pw = et_login_pw.text.toString()
            val json = JsonObject()
            json.addProperty("email",id)
            json.addProperty("password",pw)
            login(json)
        }

    }

    fun login(json: JsonObject) {
        val response: Call<UserLoginDAO> =
            ApiService.service.login(json)
        response.enqueue(object : Callback<UserLoginDAO> {
            override fun onFailure(call: Call<UserLoginDAO>, t: Throwable) {
                Log.e("ACT_LOGIN", t.toString())
                Toast.makeText(applicationContext,"인터넷 연결 상태를 확인해 주세요.",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<UserLoginDAO>,
                response: Response<UserLoginDAO>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra("id",response.body()!!.id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,"ID, PW를 확인해주세요",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
