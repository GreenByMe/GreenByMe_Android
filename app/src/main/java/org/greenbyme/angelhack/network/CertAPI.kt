package org.greenbyme.angelhack.network

import io.reactivex.Single
import org.greenbyme.angelhack.ui.certification.model.CertResult
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import retrofit2.http.GET
import retrofit2.http.Header

interface CertAPI {
    @GET("/api/page/cert/")
    fun getMissionResponse(
        @Header("jwt") token: String
    ): Single<ResponseBase<CertResult>>


}