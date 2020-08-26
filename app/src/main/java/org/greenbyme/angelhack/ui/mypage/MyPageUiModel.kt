package org.greenbyme.angelhack.ui.mypage

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem


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

