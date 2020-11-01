package org.greenbyme.angelhack.ui.certification.model


import com.google.gson.annotations.SerializedName

data class CertResult(
    @SerializedName("personalMissions")
    var personalMissions: List<CertificationItems>,
    @SerializedName("userId")
    var userId: Int
)