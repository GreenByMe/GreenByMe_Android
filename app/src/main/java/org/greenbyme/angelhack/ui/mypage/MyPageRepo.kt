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

        var foo = MutableLiveData<MyPageDAO>()
        val subscribe = ApiService.service.getUserInfo((activity as BaseActivity).getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ foo.value=it},activity::throwError)
        return foo
    }
}