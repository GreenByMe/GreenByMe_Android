package org.greenbyme.angelhack.ui.mission.detail

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.network.ApiService

class MissionDetailRepository(context: Context) {
    fun getMissionDetail(mission_id: Int): MutableLiveData<MissionDetailDAO> {
        val response: MutableLiveData<MissionDetailDAO> = MutableLiveData()
        ApiService.missionAPI.getMissionDetailResponse(mission_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { item ->
                response.value = item.data
            }
        return response
    }

    @SuppressLint("CheckResult")
    fun addMission(missionId: Int): MutableLiveData<Event<Boolean>> {
        return MutableLiveData<Event<Boolean>>().also {
            ApiService.missionAPI.joinMissionResponse(missionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it.value = Event(true) },
                    { Log.e("missiondetail", it.localizedMessage) })
        }

    }
}