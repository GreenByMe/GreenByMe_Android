package org.greenbyme.angelhack.ui.mypage

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem


class MyPageUiModel(val repo: MyPageRepo) : ViewModel() {

    var myPageDAO: LiveData<MyPageDAO> = repo.getProfile()

    fun getProfile(): LiveData<MyPageDAO> {
        myPageDAO = repo.getProfile()
        return myPageDAO
    }

    companion object {
//        private val _showPostFragment = MutableLiveData<Event<Boolean>>()
//        open val showPostFragment: LiveData<Event<Boolean>>
//            get() = _showPostFragment

        @JvmStatic
        @BindingAdapter("bind:item")
        fun bindItem(
            recyclerView: RecyclerView,
            dao: LiveData<MyPageDAO>
        ) {
            val mAdapter = HomeAdapter()
//                    .apply {
//                itemClickListener=object : HomeItemClickListener() {
//                    override fun onMissionClicked(missionId: Int, missionType: CampaignList.Type) {
//                        _showPostFragment.value= Event(true)
//                    }
//                }
//            }

            mAdapter.setItems(
                CertificationList(
                    "",
                    dao.value?.posts?.map {
                        CertificationListItem(it.postId, it.picture)
                    } ?: ArrayList<CertificationListItem>()
                    , false
                ).toList()
            )
            recyclerView.apply {
                adapter = mAdapter
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    fun onClickNickName(view: View) {
        view.context.let {
            if (myPageDAO.value == null)
                return
            // todo : 닉네임변경
        }
    }
}
object TasksListBindings {
}