package org.greenbyme.angelhack.ui.home.adapter

import org.greenbyme.angelhack.ui.home.model.CampaignList

interface  HomeItemClickListener {
     fun onCampaignClicked()
     fun onPopularCampaignClicked()
     fun onMissionClicked(missionId : Int,missionType:CampaignList.Type)
}