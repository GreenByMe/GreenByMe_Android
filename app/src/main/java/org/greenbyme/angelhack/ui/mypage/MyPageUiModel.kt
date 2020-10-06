package org.greenbyme.angelhack.ui.mypage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.ui.mypage.editprofile.EditProfileActivity


class MyPageUiModel(val repo: MyPageRepo) : ViewModel() {
    var myPageDAO: LiveData<MyPageDAO> = repo.getProfile()

    fun getProfile(): LiveData<MyPageDAO> {
        myPageDAO = repo.getProfile()
        return myPageDAO
    }


    fun onClickNickName(view: View) {
        view.context.let {
            if (myPageDAO.value == null)
                return
            val nickname = myPageDAO.value!!.nickName
            val profileImg = myPageDAO.value!!.pictureUrl

            it.startActivity(EditProfileActivity.getIntent(it, nickname, profileImg))
        }
    }
}

