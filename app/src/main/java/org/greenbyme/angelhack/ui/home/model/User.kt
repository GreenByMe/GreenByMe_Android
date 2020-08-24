package org.greenbyme.angelhack.ui.home.model

data class User(
    var userId: Int = 0,
    var nickName: String = "",
    var desc: String = "",
    var progressCampaign: Int,
    var co2: Double,
    var tree: Double,
    var rate: Int
) : HomeItem {
    override fun getViewType(): Int {
        return HomeItemViewType.HEADER.viewType
    }

    companion object {
        fun parseHomeModel(model: HomeModel): User =
            User(
                0,
                model.nickName,
                model.treeSentence,
                model.progressCampaign,
                model.expectedCO2,
                model.expectedTree,
                model.progressRates
            )
    }
}
