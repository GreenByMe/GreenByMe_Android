package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

data class NewsListItem(
    var thumbnail: String = ""
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.NEWS_LIST_ITEM.viewType
    }
}