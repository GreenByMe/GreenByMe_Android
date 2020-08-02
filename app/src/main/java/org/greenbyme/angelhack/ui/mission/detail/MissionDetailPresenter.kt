package org.greenbyme.angelhack.ui.mission.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity

class MissionDetailPresenter(val view: MissionDetailContract.View) :
    MissionDetailContract.Presenter {

    override fun getMissionDetail(mission_id: Int): Disposable? {
        return ApiService.networkMission.getMissionDetailResponse(mission_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::setMissionDetail)
    }


    override fun addMission(item: MissionDetailDAO) {
        ApiService.networkMission.PostJoinMissionResponse(MainActivity.id, item.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view::joinSucessed
            }
    }
}