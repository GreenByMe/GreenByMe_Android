package org.greenbyme.angelhack.ui.home.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import org.greenbyme.angelhack.extention.convert

data class HomeModel(
    @SerializedName("expectedCO2")
    var expectedCO2: Double,
    @SerializedName("expectedTree")
    var expectedTree: Double,
    @SerializedName("nickName")
    var nickName: String,
    @SerializedName("pageDto")
    var pageDto: PageDto,
    @SerializedName("progressCampaign")
    var progressCampaign: Int,
    @SerializedName("progressRates")
    var progressRates: Int,
    @SerializedName("treeSentence")
    var treeSentence: String

) {
    val myCampaign: CampaignList
        get() = CampaignList("진행 중인 캠페인", pageDto.progressResponseDtoList.map {
            Campaign(
                id = it.personalMissionid,
                title = it.missionTitle,
                progress = it.progress,
                memberCount = it.manyPeople,
                startDate = it.startDate,
                endDate = it.endDate,
                imageUrl = it.pictureUrl,
                missionType = CampaignList.Type.MY_CAMPAIGN
            )
        }, CampaignList.Type.MY_CAMPAIGN)

    val popularCampaign: CampaignList
        get() = CampaignList("인기 캠페인", pageDto.popularCampaignList.map {
            Campaign(
                id = it.missionId,
                title = it.subject,
                progress = it.progressCount,
                startDate = it.startDate,
                endDate = it.endDate,
                imageUrl = it.pictureUrl,
                missionType = CampaignList.Type.POPULAR
            )
        }, CampaignList.Type.POPULAR)
}

data class CampaignList(
    var title: String = "",
    var campaignList: List<Campaign> = emptyList(),
    var type: Type
) : HomeItem {
    enum class Type {
        MY_CAMPAIGN, POPULAR, POST
    }

    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_LIST.viewType
    }
}

data class Campaign(
    var id: Int = -1,
    var title: String = "",
    var progress: Int = 0,
    var imageUrl: String = "",
    var memberCount: Int = 0,
    var startDate: String = "",
    var endDate: String = "",
    var missionType: CampaignList.Type = CampaignList.Type.MY_CAMPAIGN
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

data class PageDto(
    @SerializedName("progressResponseDtoList")
    var progressResponseDtoList: List<ProgressCampaign>,
    @SerializedName("popularMissionResponseDtoList")
    var popularCampaignList: List<PopularCampaignList>
)