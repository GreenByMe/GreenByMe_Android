package org.greenbyme.angelhack.ui.mission.more

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mission_more.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionListDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.userpick.MissionPickHolder

class MissionMoreActivity : BaseActivity(), BaseAdapter.OnClickPositionListener {
    private lateinit var mAdapter: BaseAdapter<MissionListDAO.Content>
    private var missionType: CampaignList.Type? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_more)
        tag = "MISSION_MORE"
        supportActionBar?.title = "인기 캠페인"
        missionType = intent.getSerializableExtra(PARAMS_MISSION_TYPE) as CampaignList.Type?
        mAdapter = BaseAdapter(MissionPickHolder(rv_mission_popular_more_list), this)
        rv_mission_popular_more_list.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        getPopularMission()
    }

    private fun getPopularMission() =
        ApiService.missionAPI.getPopularMissionResponse()
            .map { it.data.contents }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mAdapter::setItems, this::throwError)

    override fun onClick(view: View, position: Int) {
        val missionId: Int = mAdapter.getItem(position).missionId
        startActivity(MissionDetailActivity.getIntent(this, missionId))
    }

    companion object {
        private const val PARAMS_MISSION_TYPE = "mission_type"

        fun getIntent(context: Context, missionType: CampaignList.Type): Intent {
            return Intent(context, MissionMoreActivity::class.java).apply {
                putExtra(PARAMS_MISSION_TYPE, missionType)
            }
        }
    }
}