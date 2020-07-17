package org.greenbyme.angelhack.ui.home.model

data class CampaignList(
    var title: String = "",
    var campaignList: List<CampaignListItem>
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.HORIZONTAL_LIST.viewType
    }
}