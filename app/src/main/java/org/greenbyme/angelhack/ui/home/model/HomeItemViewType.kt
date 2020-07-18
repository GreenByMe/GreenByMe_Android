package org.greenbyme.angelhack.ui.home.model

enum class HomeItemViewType(val viewType: Int) {
    EMPTY(0), HEADER(1), HORIZONTAL_LIST(2), CAMPAIGN_LIST_ITEM(3),
    NEWS_LIST(4), NEWS_LIST_ITEM(5), CERTIFICATION_LIST(6), CERTIFICATION_LIST_ITEM(7);

    companion object {
        fun get(viewType: Int): HomeItemViewType {
            return values().firstOrNull { it.viewType == viewType } ?: EMPTY
        }
    }
}