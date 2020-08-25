package org.greenbyme.angelhack.ui.home.adapter

import org.greenbyme.angelhack.ui.home.model.CampaignList

abstract class HomeItemClickListener {
    open fun onCampaignClicked() {}
    open fun onPopularCampaignClicked() {}
    abstract fun onMissionClicked(missionId: Int, missionType: CampaignList.Type)
}