package org.greenbyme.angelhack.network

import io.reactivex.Single
import org.greenbyme.angelhack.ui.certification.model.CertResult
import retrofit2.http.GET
import retrofit2.http.Path

interface CertAPI {
    @GET("/api/page/cert/users/{userId}")
    fun getMissionResponse(
        @Path("userId") userId: Long = 3
    ): Single<CertResult>
}