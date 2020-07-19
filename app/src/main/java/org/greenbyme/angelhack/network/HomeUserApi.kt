package org.greenbyme.angelhack.network

import com.google.gson.JsonObject
import io.reactivex.Single
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.data.User
import org.greenbyme.angelhack.data.UserLoginDAO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeUserApi {
    @GET("api/users/{id}")
    fun getUserInfo(@Path("id") id: Int): Single<MyPageDAO>

    @POST("api/login/users")
    fun login(
        @Body body: JsonObject
    ): Call<UserLoginDAO>
}