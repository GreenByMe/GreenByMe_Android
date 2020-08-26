package org.greenbyme.angelhack.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity


class MyPageRepo(val activity: BaseActivity) {
    fun getProfile(): LiveData<MyPageDAO> {
        MutableLiveData<MyPageDAO>().let { ret ->
            ApiService.service.getUserInfo(activity.getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ ret.value = it }, activity::throwError)
            return ret
        }
    }
}