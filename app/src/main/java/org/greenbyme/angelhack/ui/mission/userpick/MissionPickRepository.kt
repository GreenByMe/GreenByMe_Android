package org.greenbyme.angelhack.ui.mission.userpick

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.utils.Utils

class MissionPickRepository(context: Context) {

    private val missionListResponse = MutableLiveData<List<MainMissionDAO.Content>>()
    fun getMissionList(
        category: Int = 0,
        currentDate: String = "ALL"
    ): MutableLiveData<List<MainMissionDAO.Content>> {
        ApiService.missionAPI
            .getMissionResponse(
                Utils.getCategoryString(
                    category
                ), currentDate
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                missionListResponse.postValue(it.data.contents)
            }, {})

        return missionListResponse
    }
}