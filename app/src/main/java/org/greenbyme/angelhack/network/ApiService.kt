package org.greenbyme.angelhack.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        val BASE_URL = "http://cafecube.iptime.org:10080/"
        lateinit var service: HomeUserApi
        lateinit var missionAPI: MissionAPI
        lateinit var certAPI: CertAPI
        lateinit var postAPI: PostAPI

        val init by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
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

            service = retrofit.create(HomeUserApi::class.java)
            missionAPI = retrofit.create(MissionAPI::class.java)
            certAPI = retrofit.create(CertAPI::class.java)
            postAPI = retrofit.create(PostAPI::class.java)
        }
    }
}