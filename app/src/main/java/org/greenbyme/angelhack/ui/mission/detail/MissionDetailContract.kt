package org.greenbyme.angelhack.ui.mission.detail

import io.reactivex.disposables.Disposable
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.ui.BaseApplication

interface MissionDetailContract {
    interface View {
        var app : BaseApplication
        fun setMissionDetail(item: MissionDetailDAO)
        fun joinSucessed()
    }

    interface Presenter {
        fun getMissionDetail(mission_id: Int): Disposable?
        fun addMission(item: MissionDetailDAO)
    }
}