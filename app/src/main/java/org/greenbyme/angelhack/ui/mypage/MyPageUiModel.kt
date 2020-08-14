package org.greenbyme.angelhack.ui.mypage

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MyPageDAO

class MyPageUiModel(val repo: MyPageRepo) : BaseObservable(){
    var myPageDAO: LiveData<MyPageDAO> = repo.getProfile()

    fun getProfile(){
        myPageDAO = repo.getProfile()
    }

}