package org.greenbyme.angelhack.ui.mypage.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.greenbyme.angelhack.extention.Event

class EditProfileUIModel(private val repo: EditProfileRepository) {

    private var mIsCheckedNickname = MutableLiveData<Boolean>()
    val isCheckedNickname: LiveData<Boolean> = mIsCheckedNickname

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

}