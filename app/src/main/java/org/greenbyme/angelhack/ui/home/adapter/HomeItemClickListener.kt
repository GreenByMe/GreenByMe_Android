package org.greenbyme.angelhack.ui.home.adapter

interface  HomeItemClickListener {
     fun onCampaignClicked()
     fun onPopularCampaignClicked()
     fun onMissionClicked(missionId : Int)
}