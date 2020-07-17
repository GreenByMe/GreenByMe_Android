package org.greenbyme.angelhack.network

import io.reactivex.Single
import org.greenbyme.angelhack.data.User
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeUserApi {
    @GET("api/users/{id}")
    fun getUserInfo(@Path("id") id: Int) : Single<User>
}