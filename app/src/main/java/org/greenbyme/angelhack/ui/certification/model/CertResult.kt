package org.greenbyme.angelhack.ui.certification.model


import com.google.gson.annotations.SerializedName
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

data class CertResult(
    @SerializedName("missionInfoList")
    var missionInfoList: List<ProgressCampaign>,
    @SerializedName("userId")
    var userId: Int
)