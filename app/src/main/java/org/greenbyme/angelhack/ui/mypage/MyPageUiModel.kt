package org.greenbyme.angelhack.ui.mypage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.greenbyme.angelhack.data.MyPageDAO


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
            // todo : 닉네임변경
        }
    }
}

