package org.greenbyme.angelhack.ui.home.model


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("missionId")
    var missionId: Int,
    @SerializedName("progressCount")
    var progressCount: Int,
    @SerializedName("subject")
    var subject: String,
    @SerializedName("ifStartDate")
    var startDate: String,
    @SerializedName("ifEndDate")
    var endDate: String,
    @SerializedName("pictureUrl")
    var pictureUrl: String
)