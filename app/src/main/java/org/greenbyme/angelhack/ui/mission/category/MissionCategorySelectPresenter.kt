package org.greenbyme.angelhack.ui.mission.category

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.mission.MissionFragment

class MissionCategorySelectPresenter(view: MissionCategorySelectContract.View) :
    MissionCategorySelectContract.Presenter {

    private val viewControl: MissionCategorySelectContract.View = view

    override fun getMissionList(category: Int, currentDate: String): Disposable =
        ApiService.missionAPI
            .getMissionResponse(
                MissionFragment.getCategoryString(
                    category
                ), currentDate
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewControl::setMissionList)

    override fun getALLMissionList(): Disposable =
        ApiService.missionAPI
            .getMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewControl::setMissionList)
}