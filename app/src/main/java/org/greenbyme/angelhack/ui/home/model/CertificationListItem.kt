package org.greenbyme.angelhack.ui.home.model

data class CertificationListItem(
    var postId: Int,
    var thumbnail: String
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.CERTIFICATION_LIST_ITEM.viewType
    }
}
