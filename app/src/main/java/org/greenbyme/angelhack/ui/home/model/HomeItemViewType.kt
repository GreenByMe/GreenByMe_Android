package org.greenbyme.angelhack.ui.home.model

enum class HomeItemViewType(val viewType: Int) {
    EMPTY(0), HEADER(1), CAMPAIGN_LIST(2), CAMPAIGN_LIST_ITEM(3),
    CAMPAIGN_DETAIL_ITEM(4), CERTIFICATION_LIST(5), CERTIFICATION_LIST_ITEM(6);

    companion object {
        fun get(viewType: Int): HomeItemViewType {
            return values().firstOrNull { it.viewType == viewType } ?: EMPTY
        }
    }
}