package org.greenbyme.angelhack.ui.mypage

import android.content.Intent
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.ui.certification.CertificationCompleteActivity
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem


class MyPageUiModel(val repo: MyPageRepo) : ViewModel() {
    var myPageDAO: LiveData<MyPageDAO> = repo.getProfile()

    fun getProfile() {
        myPageDAO = repo.getProfile()
    }

    fun onClickNickName(view: View) {
        view.context.let {
            if (myPageDAO.value == null)
                return
            val intent = Intent(it, CertificationCompleteActivity::class.java)
            intent.putExtra("nickname", myPageDAO.value?.nickName ?: "")
            intent.putExtra("profile", myPageDAO.value?.pictureUrl ?: "")
            it.startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:item")
        fun bindItem(
            recyclerView: RecyclerView,
            dao: LiveData<MyPageDAO>
        ) {
            val mAdapter = HomeAdapter()

            mAdapter.setItems(
                listOf(
                    CertificationList(
                        "",
                        dao.value?.posts?.map {
                            CertificationListItem(it.postId, it.picture)
                        } ?: ArrayList<CertificationListItem>()
                        , false
                    )
                )
            )
            recyclerView.apply {
                adapter = mAdapter
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}