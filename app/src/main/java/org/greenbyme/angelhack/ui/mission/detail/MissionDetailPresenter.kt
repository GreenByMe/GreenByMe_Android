package org.greenbyme.angelhack.ui.mission.detail

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.network.ApiService

class MissionDetailPresenter(view: MissionDetailContract.View) :
    MissionDetailContract.Presenter {
    override val viewControl: MissionDetailContract.View = view
    override fun getMissionDetail(mission_id: Int): Disposable? {
        return ApiService.missionAPI.getMissionDetailResponse(mission_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewControl::setMissionDetail, viewControl::throwError)
    }

    override fun getMissionProgressDetail(missionInfo_id: Int): Disposable? {
        return ApiService.missionAPI.getMissionProgressDetailResponse(missionInfo_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewControl::setMissionDetail, viewControl::throwError)
    }


    @SuppressLint("CheckResult")
    override fun addMission(item: MissionDetailDAO) {
        ApiService.missionAPI.joinMissionResponse(viewControl.getToken(), item.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewControl.joinSucessed()
            }, {
                Log.e("MissionDetailPresenter", it.message ?: "")
            })
    }

}