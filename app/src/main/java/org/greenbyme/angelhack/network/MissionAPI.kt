package org.greenbyme.angelhack.network

import io.reactivex.Maybe
import io.reactivex.Single
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.data.PopularMissionDAO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MissionAPI {
    //Available values : NONE, ENERGY, DISPOSABLE, TRAFFIC, WATERWORKS, CAMPAIGN
    //Available values : DAY, WEEK, MONTH
    @GET("/api/missions/categorys/{category}/daycategory/{dateCategory}")
    fun getMissionResponse(
        @Path("category") category: String = "CAMPAIGN",
        @Path("dateCategory") dateCategory: String = "DAY"
    ): Single<MainMissionDAO>

    @GET("/api/missions")
    fun getAllMissionResponse(
    ): Single<MainMissionDAO>

    @GET("/api/missions/{mission_id}")
    fun getMissionDetailResponse(
        @Path("mission_id") mission_id: Int
    ): Single<MissionDetailDAO>

    @GET("/api/missionInfos/{mission_id}")
    fun getMissionProgressDetailResponse(
        @Path("mission_id") mission_id: Int
    ): Single<MissionDetailDAO>

    @POST("/api/missionInfos/missions/{missionId}")
    fun joinMissionResponse(
        @Header("jwt") token: String,
        @Path("missionId") missionId: Int
    ): Maybe<MissionDetailDAO>

    @GET("/api/missions/populars")
    fun getPopularMission(
    ): Single<PopularMissionDAO>


}