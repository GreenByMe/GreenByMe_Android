package org.greenbyme.angelhack.ui.mission.category

import io.reactivex.disposables.Disposable
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.BasePresenter
import org.greenbyme.angelhack.ui.BaseView


interface MissionCategorySelectContract {
    interface Presenter : BasePresenter {
        fun getMissionList(category: Int, currentDate: String): Disposable?
        fun getALLMissionList(): Disposable?
    }

    interface View : BaseView<Presenter> {
        fun setMissionList(response: MainMissionDAO?)
    }
}