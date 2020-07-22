package org.greenbyme.angelhack.network

import io.reactivex.Single
import org.greenbyme.angelhack.data.MainMissionDAO
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageAPI {
    //Available values : NONE, ENERGY, DISPOSABLE, TRAFFIC, WATERWORKS, CAMPAIGN
    //Available values : DAY, WEEK, MONTH
    @GET("/api/users/{userId}")
    fun getMissionResponse(
        @Path("category") category: Int
    ): Single<MainMissionDAO>

    @GET("/api/missions")
    fun getMissionResponse(
    ): Single<MainMissionDAO>
}