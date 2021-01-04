package org.greenbyme.angelhack.ui.mypage.editprofile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.greenbyme.angelhack.extention.Event

class EditProfileViewModel(private val repo: EditProfileRepository) {

    private var mIsCheckedNickname = MutableLiveData<Boolean>()
    val isCheckedNickname: LiveData<Boolean> = mIsCheckedNickname

    private var mIsClickedLogout = MutableLiveData<Event<Boolean>>()
    val isClickedLogout: LiveData<Event<Boolean>> = mIsClickedLogout

    val mNickname = MutableLiveData<String>()
    val nickname: LiveData<String> = mNickname

    private val mIsFinished = MutableLiveData<Event<Boolean>>()
    val isFinished: LiveData<Event<Boolean>> = mIsFinished

    fun checkNickname(nickname: String) {
        repo.checkNickname(nickname, mIsCheckedNickname)
    }

    fun onClickSave(nickname: String) {
        if (mIsCheckedNickname.value == true) {
            repo.putNickname(nickname)
            mIsFinished.value = Event(true)
        }
    }

    fun onClickLogout(view: View) {
        mIsClickedLogout.value = Event<Boolean>(true)
    }

}