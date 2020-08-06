package org.greenbyme.angelhack.network

import com.google.gson.JsonObject
import io.reactivex.Maybe
import io.reactivex.Single
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.data.UserLoginDAO
import org.greenbyme.angelhack.ui.home.model.HomeModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeUserApi {
    @GET("api/page/users/{id}")
    fun getUserInfo(@Path("id") id: Int): Single<MyPageDAO>

    @POST("api/users/signin")
    fun login(
        @Body body: JsonObject
    ): Maybe<String>

    @POST("/api/users")
    fun signUp(
        @Body body: JsonObject
    ): Single<UserLoginDAO>

    @GET("api/page/home/users/{id}")
    fun getUserHomeInfo(@Path("id") id: Int): Single<HomeModel>


}