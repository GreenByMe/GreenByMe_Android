package org.greenbyme.angelhack.network

import io.reactivex.Maybe
import io.reactivex.Single
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDetailDAO
import retrofit2.Call
import retrofit2.http.GET
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

    @POST("/api/missionInfos/users/{userId}/missions/{missionId}")
    fun PostJoinMissionResponse(
        @Path("userId") userId: Int,
        @Path("missionId") missionId: Int
    ): Maybe<MissionDetailDAO>

}