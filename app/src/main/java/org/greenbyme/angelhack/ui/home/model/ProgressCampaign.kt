package org.greenbyme.angelhack.ui.home.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgressCampaign(



    @SerializedName("subject")
    var subject: String? = null,
    @SerializedName("finishCount")
    var finishCount: Int,
    @SerializedName("manyPeople")
    var manyPeople: Int,
    @SerializedName("personalMission_id")
    var personalMissionid: Int,
    @SerializedName("missionTitle")
    var missionTitle: String,
    @SerializedName("pictureUrl")
    var pictureUrl: String,
    @SerializedName("progress")
    var progress: Int,
    @SerializedName("startDate")
    var startDate: String,
    @SerializedName("endDate")
    var endDate: String,
    @SerializedName("certifiaciontTest")
    var certifiaciontTest: String? = null,
    @SerializedName("category")
    var category: CampaignCategory? = null
) : Parcelable