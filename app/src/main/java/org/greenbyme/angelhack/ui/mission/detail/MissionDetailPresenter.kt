package org.greenbyme.angelhack.ui.mission.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.network.ApiService

class MissionDetailPresenter(private val baseActivity: MissionDetailActivity) :
    MissionDetailContract.Presenter {
    override val viewControl: MissionDetailContract.View = baseActivity
    override fun getMissionDetail(mission_id: Int): Disposable? {
        return ApiService.missionAPI.getMissionDetailResponse(mission_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewControl::setMissionDetail, baseActivity::throwError)
    }

    override fun addMission(item: MissionDetailDAO) {
        val subscribe =
            ApiService.missionAPI.joinMissionResponse(baseActivity.getToken(), item.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewControl.joinSucessed()
                }
    }
}