package org.greenbyme.angelhack.ui.mypage.editprofile

import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.local.BaseRepository
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.utils.AutoClearDisposable

class EditProfileRepository(val activity: BaseActivity) : BaseRepository {
    override val autoClearDisposable: AutoClearDisposable
        get() = AutoClearDisposable(activity)

    override fun observeLifeCycle() {
        activity.lifecycle.addObserver(autoClearDisposable)
    }

    fun putNickname(nickname: String) =
        autoClearDisposable.add(
            ApiService.mypageAPI.updateNickname(activity.getToken(), nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )


    fun checkNickname(nickname: String,data :MutableLiveData<Boolean>)=
            autoClearDisposable.add(
                ApiService.mypageAPI.checkNickname(nickname)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        data.value = it.data
                    }, activity::throwError)
            )


}