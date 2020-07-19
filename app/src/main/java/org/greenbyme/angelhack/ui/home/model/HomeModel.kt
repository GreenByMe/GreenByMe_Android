package org.greenbyme.angelhack.ui.home.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import org.greenbyme.angelhack.extention.convert
import java.text.SimpleDateFormat

data class HomeModel(
    @SerializedName("expectedCO2")
    var expectedCO2: Int,
    @SerializedName("expectedTree")
    var expectedTree: Int,
    @SerializedName("nickName")
    var nickName: String,
    @SerializedName("popularMissionResponseDtoList")
    var popularCampaignList: PopularCampaignList,
    @SerializedName("progressCampaign")
    var progressCampaign: Int,
    @SerializedName("progressRates")
    var progressRates: Int,
    @SerializedName("progressResponseDtoList")
    var progressResponseDtoList: List<ProgressCampaign>,
    @SerializedName("treeSentence")
    var treeSentence: String


) {
    val myCampaign: CampaignList
        get() = CampaignList("진행 중인 캠페인", progressResponseDtoList.map {
            Campaign(
                title = it.missionTitle, progress = it.progress, memberCount = it.manyPeople,
                startDate = it.startDate, endDate = it.endDate, imageUrl = it.pictureUrl
            )
        }, CampaignList.Type.MY_CAMPAIGN)

    val popularCampaign: CampaignList
        get() = CampaignList("인기 캠페인", popularCampaignList.content.map {
            Campaign(
                title = it.subject, progress = it.progressCount, startDate = it.startDate,
                endDate = it.endDate, imageUrl = it.pictureUrl
            )
        }, CampaignList.Type.POPULAR)
}

data class CampaignList(
    var title: String = "",
    var campaignList: List<Campaign> = emptyList(),
    var type: Type
) : HomeItem {
    enum class Type {
        MY_CAMPAIGN, POPULAR
    }

    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_LIST.viewType
    }
}

data class Campaign(
    var title: String = "",
    var progress: Int = 0,
    var imageUrl: String = "",
    var memberCount: Int = 0,
    var startDate: String = "",
    var endDate: String
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_LIST_ITEM.viewType
    }

    @SuppressLint("SimpleDateFormat")
    fun getDueDate(): String {
        val fromFormat = "yyyy-MM-dd'T'HH:mm:ss"
        val toFormat = "M/d"

        val from = startDate.convert(fromFormat, toFormat)
        val to = endDate.convert(fromFormat, toFormat)
        return "$from~$to"
    }
}