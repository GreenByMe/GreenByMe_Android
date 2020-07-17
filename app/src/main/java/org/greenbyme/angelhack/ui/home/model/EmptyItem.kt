package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

class EmptyItem : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.EMPTY.viewType
    }
}