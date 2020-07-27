package org.greenbyme.angelhack.ui.home.model

data class ProgressItem(
    var img: String,
    var title: String,
    var userCnt: String,
    var date: String,
    var rate: String,
    var maxCnt: Int,
    var curCnt: Int
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.CAMPAIGN_DETAIL_ITEM.viewType
    }
}
