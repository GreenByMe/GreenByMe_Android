package org.greenbyme.angelhack.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL = "http://ec2-15-165-113-25.ap-northeast-2.compute.amazonaws.com:8080"

    private val homeUserApi: HomeUserApi by lazy {

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

        return@lazy retrofit.create(HomeUserApi::class.java)
    }

    companion object{
        val service = ApiService().homeUserApi
    }
}