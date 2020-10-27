package org.greenbyme.angelhack.ui.mission.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.extention.Event

class MissionDetailViewModel(val repository: MissionDetailRepository, val missionId: Int) :
    ViewModel() {
    val getDetail: MutableLiveData<MissionDetailDAO> =
        repository.getMissionDetail(missionId)

    private var addMission = MutableLiveData<Event<Boolean>>()
    val mAddMission: LiveData<Event<Boolean>> = addMission

    fun joinMission(view : View) {
        addMission = repository.addMission(missionId)
    }

}