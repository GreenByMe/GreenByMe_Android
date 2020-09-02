package org.greenbyme.angelhack.ui.home.model


import com.google.gson.annotations.SerializedName

data class PopularCampaignList(
    @SerializedName("missionId")
    var missionId: Int,
    @SerializedName("progressCount")
    var progressCount: Int,
    @SerializedName("subject")
    var subject: String,
    @SerializedName("startDate")
    var startDate: String,
    @SerializedName("endDate")
    var endDate: String,
    @SerializedName("pictureUrl")
    var pictureUrl: String
)