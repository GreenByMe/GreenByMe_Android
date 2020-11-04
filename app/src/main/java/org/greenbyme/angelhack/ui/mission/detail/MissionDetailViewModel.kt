package org.greenbyme.angelhack.ui.mission.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.ui.home.model.CampaignList

class MissionDetailViewModel(val repository: MissionDetailRepository, val missionId: Int, val missionType: CampaignList.Type) :
    ViewModel() {
    val missionDetail: MutableLiveData<MissionDetailDAO> = repository.getMissionDetail(missionId)


//    val missionDetail: MutableLiveData<MissionDetailDAO> by lazy {
//        if (missionType == CampaignList.Type.MY_CAMPAIGN) {
//            repository.getPersonalMissionDetail(missionId)
//        } else {
//            repository.getMissionDetail(missionId)
//        }
//    }

    val otherUserPost =
        repository.getOtherUserFeed(missionId)

    private var addMission = MutableLiveData<Event<Boolean>>()
    val mAddMission: LiveData<Event<Boolean>> = addMission

    fun joinMission(view: View) {
        addMission = repository.addMission(missionId)
    }

}