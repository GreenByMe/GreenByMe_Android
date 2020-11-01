package org.greenbyme.angelhack.network

import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.MultipartBody
import org.greenbyme.angelhack.ui.certification.model.PostResponse
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.ui.mypage.post.FeedPostDao
import retrofit2.http.*

interface PostAPI {
    @Multipart
    @POST("/api/posts")
    fun postCertification(
        @Query("personalMission_id") personalMissionId: Int,
        @Query("open") open: Boolean,
        @Query("text") text: String,
        @Query("title") title: String,
        @Part file: MultipartBody.Part
    ): Single<ResponseBase<PostResponse>>


    @GET("/api/posts/{postId}")
    fun getPosts(
        @Path("postId") postId: Int
    ): Flowable<ResponseBase<FeedPostDao>>
}