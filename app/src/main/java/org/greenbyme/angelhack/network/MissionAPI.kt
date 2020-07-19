package org.greenbyme.angelhack.network

import org.greenbyme.angelhack.data.MainMissionDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MissionAPI {
    //Available values : NONE, ENERGY, DISPOSABLE, TRAFFIC, WATERWORKS, CAMPAIGN
    //Available values : DAY, WEEK, MONTH
    @GET("/api/missions/categorys/{category}/daycategory/{dateCategory}")
    fun getMissionResponse(
        @Path("category") category: String = "CAMPAIGN",
        @Path("dateCategory") dateCategory: String = "DAY"
    ): Call<MainMissionDAO>

    @GET("/api/missions")
    fun getMissionResponse(
    ): Call<MainMissionDAO>
}