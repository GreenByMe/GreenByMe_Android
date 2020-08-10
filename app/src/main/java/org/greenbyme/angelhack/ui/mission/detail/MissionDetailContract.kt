package org.greenbyme.angelhack.ui.mission.detail

import io.reactivex.disposables.Disposable
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.ui.BasePresenter
import org.greenbyme.angelhack.ui.BaseView

interface MissionDetailContract {
    interface View : BaseView<Presenter>{
        fun setMissionDetail(item: MissionDetailDAO)
        fun joinSucessed()
    }

    interface Presenter : BasePresenter {
        val viewControl : View
        fun getMissionDetail(mission_id: Int): Disposable?
        fun addMission(item: MissionDetailDAO)
    }
}