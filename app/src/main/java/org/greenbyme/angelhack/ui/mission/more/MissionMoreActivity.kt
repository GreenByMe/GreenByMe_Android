package org.greenbyme.angelhack.ui.mission.more

import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mission_more.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.Campaign
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.model.HomeItem

class MissionMoreActivity : BaseActivity() {
    private val homeAdapter = HomeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_more)
        tag="MISSION_MORE"

        rv_mission_more_list.adapter = HomeAdapter()
        getPopularMission()
    }

    private fun getPopularMission() =
        ApiService.missionAPI.getPopularMission()
            .map { it ->
                listOf<HomeItem>(
                    CampaignList("", it.content.map { item ->
                        Campaign(
                            item.missionId,
                            item.subject,
                            item.passCandidatesCount,
                            item.category,
                            item.passCandidatesCount
                        )
                    }, CampaignList.Type.POPULAR)
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(homeAdapter::setItems,this::throwError)
}