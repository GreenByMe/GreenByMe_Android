package org.greenbyme.angelhack.network

import io.reactivex.Single
import okhttp3.MultipartBody
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface MyPageAPI {
    //TODO : Require API UPDATE
    @PUT("/api/users/image")
    fun updateProfileImage(
        @Part file: MultipartBody.Part
    ): Single<ResponseBase<MainMissionDAO>>


    //TODO : Require API UPDATE
    @PUT("/api/users/nickname")
    fun updateNickname(
        @Query("nickname") nickname: String
    ):  Single<ResponseBase<MainMissionDAO>>
}