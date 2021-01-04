package org.greenbyme.angelhack.ui.home.model

import com.google.gson.annotations.SerializedName

data class ProgressItem(
    val contents: List<Content>,
    val pageNumber: Int,
    val totalPage: Int
) : HomeItem {
    data class Content(
        @SerializedName("endDate")
        val endDate: String,
        @SerializedName("finishCount")
        val finishCount: Int,
        @SerializedName("manyPeople")
        val manyPeople: Int,
        @SerializedName("missionId")
        val missionId: Int,
        @SerializedName("missionPictureUrl")
        val missionPictureUrl: String,
        @SerializedName("missionTitle")
        var missionTitle: String,
        @SerializedName("personalMissionId")
        val personalMissionId: Int,
        @SerializedName("progress")
        val progress: Int,
        @SerializedName("remainPeriod")
        val remainPeriod: RemainPeriod,
        @SerializedName("startDate")
        val startDate: String
    )

    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_DETAIL_ITEM.viewType
    }

    data class RemainPeriod(
        val remainDay: Int,
        val remainHour: Int,
        val remainMin: Int
    )
}