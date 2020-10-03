package org.greenbyme.angelhack.ui.certification.model

import org.greenbyme.angelhack.ui.home.model.HomeItem

data class CertificationItem(
    var thumbnail: String,
    var title: String,
    var data: String
) : HomeItem {
    override fun getViewType(): Int {
        return 1
    }
}