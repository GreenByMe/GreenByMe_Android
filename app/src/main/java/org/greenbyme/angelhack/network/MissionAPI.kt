package org.greenbyme.angelhack.network

import org.greenbyme.angelhack.data.MainMissionDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MissionAPI {
    //all category
    //Available values : NONE, ENERGY, DISPOSABLE, TRAFFIC, WATERWORKS, CAMPAIGN
    @GET("/api/missions/categorys/{category}")
    fun getMissionResponse(
        @Path("category") category: String = "CAMPAIGN"
    ): Call<MainMissionDTO>
}