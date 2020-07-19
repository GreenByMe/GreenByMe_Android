package org.greenbyme.angelhack.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL = "http://ec2-13-58-104-154.us-east-2.compute.amazonaws.com:8080/"

    val init by lazy {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service=retrofit.create(HomeUserApi::class.java)
        networkMission=retrofit.create(MissionAPI::class.java)
    }

    companion object {
        lateinit var service: HomeUserApi
        lateinit var networkMission: MissionAPI
    }
}