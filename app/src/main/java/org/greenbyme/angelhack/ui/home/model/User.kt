package org.greenbyme.angelhack.ui.home.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nickName")
    var nickName: String = "",
    @SerializedName("treeSentence")
    var treeSentence: String = "",
    @SerializedName("progressCampaign")
    var progressCampaign: Int,
    @SerializedName("expectedCO2")
    var expectedCO2: Double,
    @SerializedName("expectedTree")
    var expectedTree: Double,
    @SerializedName("progressRates")
    var progressRates: Int
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.HEADER.viewType
    }

//    companion object {
//        fun parseHomeModel(model: HomeModel): User =
//            User(
//                model.nickName,
//                model.treeSentence,
//                model.progressCampaign,
//                model.,
//                model.expectedTree,
//                model.progressRates
//            )
//    }
}
