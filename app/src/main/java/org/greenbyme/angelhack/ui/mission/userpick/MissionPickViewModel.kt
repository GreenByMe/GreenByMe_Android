package org.greenbyme.angelhack.ui.mission.userpick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject
import org.greenbyme.angelhack.data.MainMissionDAO

class MissionPickViewModel(private val repository: MissionPickRepository) : ViewModel() {
    private val _category = BehaviorSubject.createDefault(0)
    private val _datCategory = BehaviorSubject.createDefault("ALL")
    private var _missionList = MutableLiveData<List<MainMissionDAO.Content>>()
    val missionList: LiveData<List<MainMissionDAO.Content>> = _missionList

    fun updateMissionList(category: Int, date: String) {
        repository.getMissionList(category, date).observeForever {
            _missionList.value = it
        }
        _category.onNext(category)
        _datCategory.onNext(date)
    }
}