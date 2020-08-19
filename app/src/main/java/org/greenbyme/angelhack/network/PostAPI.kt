package org.greenbyme.angelhack.network

import io.reactivex.Single
import okhttp3.MultipartBody
import org.greenbyme.angelhack.ui.certification.model.CertResult
import retrofit2.http.*

interface PostAPI {
    @Multipart
    @POST("/api/posts")
    fun postCertification(
        @Header("jwt")token:String,
        @Query("missionInfoId") missionInfoId: Int,
        @Query("open") open: Boolean,
        @Query("text") text: String,
        @Query("title") title: String,
        @Query("userId") userId: Int,
        @Part file: MultipartBody.Part
    ): Single<CertResult>
}