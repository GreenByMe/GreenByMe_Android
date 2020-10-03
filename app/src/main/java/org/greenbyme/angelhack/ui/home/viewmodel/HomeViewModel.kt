package org.greenbyme.angelhack.ui.home.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.home.model.HomeItem

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mLoadingData = MutableLiveData<Boolean>()
    private val mSubHomeItemList = MutableLiveData<List<HomeItem>>()

    val homeData: LiveData<List<HomeItem>> = mSubHomeItemList
    val loadingData: LiveData<Boolean> = mLoadingData

    @SuppressLint("CheckResult")
    fun loadUserHomeInfo(token: String) {
        ApiService.service.getUserHomeInfo(token)
            .map {

                listOf(
                    it.data.userHomePageDetailDto,
                    it.data.myCampaign,
                    it.data.popularCampaign
                )
            }.subscribeOn(Schedulers.io())
            .doOnSubscribe {
                mLoadingData.postValue(true)
            }
            .doFinally {
                mLoadingData.postValue(false)
            }
            .subscribe({
                mSubHomeItemList.postValue(it)
            }, {
                Log.e("TAG_SHOW_ERR", it.toString())
            })
    }
}