package org.greenbyme.angelhack.network

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import okhttp3.MultipartBody
import org.greenbyme.angelhack.ui.certification.model.CertResult
import org.greenbyme.angelhack.ui.certification.model.PostResponse
import org.greenbyme.angelhack.ui.mypage.post.FeedPostDao
import retrofit2.http.*

interface PostAPI {
    @Multipart
    @POST("/api/posts")
    fun postCertification(
        @Header("jwt") token: String,
        @Query("personalMission_id") personalMissionId: Int,
        @Query("open") open: Boolean,
        @Query("text") text: String,
        @Query("title") title: String,
        @Query("userId") userId: Int,
        @Part file: MultipartBody.Part
    ): Single<PostResponse>


    @GET("/api/posts/{postId}")
    fun getPosts(
            @Path("postId")postId:Int
    ): Flowable<FeedPostDao>
}