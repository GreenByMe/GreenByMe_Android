package org.greenbyme.angelhack.ui.home.model

import android.media.ThumbnailUtils
import java.util.*

data class User(
    var userId: Int = 0,
    var nickName: String = "",
    var desc: String = "",
    var progressCampaign: Int,
    var co2: Int,
    var tree: Int,
    var rate: Int
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.HEADER.viewType
    }
}
