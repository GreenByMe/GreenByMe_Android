package org.greenbyme.angelhack.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem

class MyPageRepo(val activity: BaseActivity) {
    fun getProfile(): LiveData<MyPageDAO> {
        MutableLiveData<MyPageDAO>().let { ret ->
            ApiService.service.getUserInfo(activity.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ret.value = it }, activity::throwError)
            return ret
        }
    }

    fun bind(
        recyclerView: RecyclerView,
        dao: LiveData<MyPageDAO>
    ) {
        val mAdapter = HomeAdapter().apply {
            itemClickListener = object : HomeItemClickListener() {
                override fun onMissionClicked(missionId: Int, missionType: CampaignList.Type) {

                }
            }
        }

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