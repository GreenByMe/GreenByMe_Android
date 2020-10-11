package org.greenbyme.angelhack.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        var token: String = ""
        private const val BASE_URL = "https://cafecube.iptime.org"

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

        val service: HomeUserAPI by lazy {
            mRetrofit.create(HomeUserAPI::class.java)
        }

        val missionAPI: MissionAPI by lazy {
            mRetrofit.create(MissionAPI::class.java)
        }

        val certAPI: CertAPI by lazy {
            mRetrofit.create(CertAPI::class.java)
        }

        val postAPI: PostAPI by lazy {
            mRetrofit.create(PostAPI::class.java)
        }

        val mypageAPI: MyPageAPI by lazy {
            mRetrofit.create(MyPageAPI::class.java)
        }
    }
}