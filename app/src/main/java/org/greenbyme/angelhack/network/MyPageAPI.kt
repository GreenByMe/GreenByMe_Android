package org.greenbyme.angelhack.network

import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import retrofit2.http.*

interface MyPageAPI {
    //TODO : Require API UPDATE
    @PUT("/api/users/image")
    fun updateProfileImage(
        @Part file: MultipartBody.Part
    ): Single<ResponseBase<MainMissionDAO>>


    //TODO : Require API UPDATE
    @PUT("/api/users")
    fun updateNickname(
        @Header("jwt") token:String,
        @Query("nickName") nickname: String
    ): Completable

    @GET("/api/users/nickname/{nickname}")
    fun checkNickname(
        @Path("nickname") nickname: String
    ): Single<ResponseBase<Boolean>>
}