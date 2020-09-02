package org.greenbyme.angelhack.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.data.local.BaseRepository
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.utils.AutoClearDisposable


class MyPageRepo(val activity: BaseActivity) : BaseRepository {
    override val autoClearDisposable: AutoClearDisposable
        get() = AutoClearDisposable(activity)

    override fun observeLifeCycle() {
        activity.lifecycle.addObserver(autoClearDisposable)
    }

    fun getProfile(): LiveData<MyPageDAO> {
        MutableLiveData<MyPageDAO>().let { ret ->
            autoClearDisposable.add(ApiService.service.getUserInfo(activity.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ret.value = it.data }, activity::throwError))
            return ret
        }
    }
}