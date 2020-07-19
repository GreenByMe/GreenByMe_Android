package org.greenbyme.angelhack.ui.home.model


import com.google.gson.annotations.SerializedName

data class MissionInfo(
    @SerializedName("finishCount")
    var finishCount: Int,
    @SerializedName("missionId")
    var missionId: Int,
    @SerializedName("missionInfoId")
    var missionInfoId: Int,
    @SerializedName("missionInfoStatus")
    var missionInfoStatus: String,
    @SerializedName("progress")
    var progress: Int,
    @SerializedName("remainPeriod")
    var remainPeriod: Int,
    @SerializedName("userId")
    var userId: Int
)