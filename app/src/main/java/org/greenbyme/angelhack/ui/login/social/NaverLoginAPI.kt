package org.greenbyme.angelhack.ui.login.social

import android.os.AsyncTask
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.greenbyme.angelhack.network.NaverUserAPI
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NaverLoginAPI {
    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"

        private val mRetrofit: Retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setLenient().create()
                    )
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val naverService: NaverUserAPI by lazy {
            mRetrofit.create(NaverUserAPI::class.java)
        }
    }
}

class NaverProfileGet(var accessToken: String) : AsyncTask<String?, Void?, String>() {
    //네이버 프로필 조회 API에 보낼 헤더. 그대로 쓰면 된다.
    var header = "Bearer $accessToken"

    //네이버 프로필 조회 API에서 받은 jSON에서 원하는 데이터를 뽑아내는 부분
    //여기서는 닉네임, 프로필사진 주소, 이메일을 얻어오지만, 다른 값도 얻어올 수 있다.
    //이 부분을 원하는 대로 수정하면 된다.
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        try {

//            val intent = Intent(this, MainActivity::class.java)
            val jsonObject1 = JSONObject(result)
            val jsonObject2 = jsonObject1["response"] as JSONObject

            val image = jsonObject2.getString("profile_image")
            val id = jsonObject2.getString("id")
            Log.e("네아로아이디", id)
//            val name = jsonObject2.getString("name")
//            val email = jsonObject2.getString("email")
//            intent.putExtra("Nickname", name)
//            intent.putExtra("Image", image)
//            intent.putExtra("Email", email)
//            ContextCompat.startActivity(intent)
//            finish()
        } catch (e: Exception) {
        }
    }

    override fun doInBackground(vararg p0: String?): String {

        //네이버 프로필 조회 API에서 프로필을 jSON 형식으로 받아오는 부분.
        //이 부분도 그대로 사용하면 된다.
        val response = StringBuffer()
        try {
            val apiURL = "https://openapi.naver.com/v1/nid/me"
            val url = URL(apiURL)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Authorization", header)
            val responseCode: Int = conn.responseCode
            val br: BufferedReader
            if (responseCode == 200) {
                br = BufferedReader(InputStreamReader(conn.inputStream))
            } else {
                br = BufferedReader(InputStreamReader(conn.errorStream))
            }
            var inputLine: String?
            while (br.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response.toString()
    }
}