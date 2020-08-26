package org.greenbyme.angelhack.ui.home.model

import org.greenbyme.angelhack.ui.home.model.HomeItemViewType

data class CertificationList(
    var title: String = "",
    var certificationList: List<CertificationListItem> = ArrayList<CertificationListItem>(),
    var isVisibleTitle: Boolean = true
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.CERTIFICATION_LIST.viewType
    }

    fun toList(): List<CertificationList> {
        return listOf(this)
    }

}