package org.greenbyme.angelhack.network

import com.google.gson.JsonObject
import io.reactivex.Maybe
import io.reactivex.Single
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.data.UserLoginDAO
import org.greenbyme.angelhack.ui.home.model.HomeModel
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.ui.login.LoginDAO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeUserAPI {
    @GET("api/page/")
    fun getUserInfo(
        @Header("jwt") token: String
    ): Single<ResponseBase<MyPageDAO>>

    @POST("api/users/signin")
    fun idLogin(
        @Body body: JsonObject
    ): Maybe<LoginDAO>

    @POST("api/users/refresh")
    fun tokenLogin(
        @Header("jwt") token: String
    ): Maybe<LoginDAO>

    @POST("/api/users/signup")
    fun signUp(
        @Body body: JsonObject
    ): Single<ResponseBase<UserLoginDAO>>

    @GET("api/page/home/")
    fun getUserHomeInfo(
        @Header("jwt") token: String
    ): Single<ResponseBase<HomeModel>>

    @POST("/api/users/signup/social")
    fun socialSignUp(
        @Body body: JsonObject
    ): Single<ResponseBase<String>>

    @POST("/api/users/signin/social")
    fun socialLogin(
        @Body body: JsonObject
    ): Maybe<LoginDAO>
}