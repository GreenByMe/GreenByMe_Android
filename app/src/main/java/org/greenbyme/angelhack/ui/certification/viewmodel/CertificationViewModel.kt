package org.greenbyme.angelhack.ui.certification.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.certification.CertificationAdapter
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

class CertificationViewModel(application: Application) : AndroidViewModel(application) {
    private val mCertAdapter = CertificationAdapter()
    private val mSubCertItemList = MutableLiveData<List<ProgressCampaign>>()

    private val _showNoMissionFragment = MutableLiveData<Event<Boolean>>()
    val showNoMissionFragment: LiveData<Event<Boolean>>
        get() = _showNoMissionFragment

    val certData = mSubCertItemList

    @SuppressLint("CheckResult")
    fun loadCertData(token: String) {
        ApiService.certAPI.getMissionResponse(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.data.personalMissions.let {
                    if (it.size == 0) {
                        _showNoMissionFragment.value = Event(true)
                    } else {
                        mSubCertItemList.postValue(it)
                    }
                }
            },
                {
                    Log.e("CertificationFragment", it.message)
                })
    }
}