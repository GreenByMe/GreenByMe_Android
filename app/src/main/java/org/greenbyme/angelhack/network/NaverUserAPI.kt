package org.greenbyme.angelhack.network

import io.reactivex.Single
import org.greenbyme.angelhack.data.NaverUserDAO
import retrofit2.http.GET
import retrofit2.http.Header

interface NaverUserAPI {
    @GET("v1/nid/me")
    fun getUserID(
        @Header("Authorization") token: String
    ): Single<NaverUserDAO>
}