package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

data class CampaignListItem(
    var name: String = "",
    var tagText: String = "",
    var thumbnail : String = "",
    var data : String,
    var nums : String
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_LIST_ITEM.viewType
    }
}