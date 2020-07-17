package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

data class Header(
    var userName: String = ""
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.HEADER.viewType
    }
}