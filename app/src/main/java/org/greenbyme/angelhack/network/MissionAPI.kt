package org.greenbyme.angelhack.network

import io.reactivex.Completable
import io.reactivex.Single
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.data.MissionListDAO
import org.greenbyme.angelhack.ui.home.model.ProgressItem
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MissionAPI {
    //Available values : NONE, ENERGY, DISPOSABLE, TRAFFIC, WATERWORKS, CAMPAIGN
    //Available values : DAY, WEEK, MONTH
    @GET("/api/missions/categorys/{category}/daycategory/{dateCategory}")
    fun getMissionResponse(
        @Path("category") category: String = "ALL",
        @Path("dateCategory") dateCategory: String = "ALL"
    ): Single<ResponseBase<MainMissionDAO>>

    @GET("/api/missions/{mission_id}")
    fun getMissionDetailResponse(
        @Path("mission_id") mission_id: Int
    ): Single<ResponseBase<MissionDetailDAO>>

    @GET("/api/missions/populars")
    fun getPopularMissionResponse(
    ): Single<ResponseBase<MissionListDAO>>

    /*
    *  Personal Mission
    */
    @GET("/api/personalmissions")
    fun getPersonalMissionResponse(
        @Header("jwt") token: String
    ): Single<ResponseBase<ProgressItem>>


    @GET("/api/personalmissions/{mission_id}")
    fun getMissionProgressDetailResponse(
        @Path("mission_id") mission_id: Int
    ): Single<ResponseBase<MissionDetailDAO>>

    @POST("/api/personalmissions/missions/{missionId}")
    fun joinMissionResponse(
        @Path("missionId") missionId: Int
    ): Completable


}