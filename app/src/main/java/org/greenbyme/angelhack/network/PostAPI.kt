package org.greenbyme.angelhack.network

import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenbyme.angelhack.ui.certification.model.CertResult
import retrofit2.http.*

interface PostAPI {
    @Multipart
    @POST("/api/posts")
    fun postCertification(
        @Query("missionInfoId") missionInfoId:Int,
        @Query("open") open:Boolean,
        @Query("text") text:String,
        @Query("title") title:String,
        @Query("userId") userId:Int,
        //@Part("body") body: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<CertResult>
}