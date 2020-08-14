package org.greenbyme.angelhack.network

import io.reactivex.Single
import okhttp3.MultipartBody
import org.greenbyme.angelhack.data.MainMissionDAO
import retrofit2.http.*

interface MyPageAPI {
    //TODO : Require API UPDATE
    @PUT("/api/users/image")
    fun updateProfileImage(
        @Part file: MultipartBody.Part
    ): Single<MainMissionDAO>


    //TODO : Require API UPDATE
    @PUT("/api/users/nickname")
    fun updateNickname(
        @Query("nickname")nickname: String
    ): Single<MainMissionDAO>
}