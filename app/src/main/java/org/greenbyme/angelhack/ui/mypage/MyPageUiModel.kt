package org.greenbyme.angelhack.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.greenbyme.angelhack.data.MyPageDAO

class MyPageUiModel(val repo: MyPageRepo) : ViewModel() {
    var myPageDAO: LiveData<MyPageDAO> = repo.getProfile()

    fun getProfile() {
        myPageDAO = repo.getProfile()
    }

}