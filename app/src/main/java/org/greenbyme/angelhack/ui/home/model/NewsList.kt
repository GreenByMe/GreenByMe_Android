package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

data class NewsList(
    var title: String = "",
    var newsList : List<NewsListItem>
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.NEWS_LIST.viewType
    }
}