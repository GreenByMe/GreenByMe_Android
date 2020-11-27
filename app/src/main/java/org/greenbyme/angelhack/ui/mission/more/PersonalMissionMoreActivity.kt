package org.greenbyme.angelhack.ui.mission.more

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_personal_mission_more.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.model.ProgressItem
import org.greenbyme.angelhack.ui.home.viewholder.ProgressViewHolder
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity

class PersonalMissionMoreActivity : BaseActivity(), BaseAdapter.OnClickPositionListener {
    private lateinit var mAdapter: BaseAdapter<ProgressItem.Content>

    override fun onClick(view: View, position: Int) {
        val missionId = mAdapter.getItem(position).personalMissionId
        startActivity(MissionDetailActivity.getIntent(this, missionId))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_mission_more)
        init()
    }

    private fun init() {
        tag = "MISSION_MORE"
        supportActionBar?.title = "진행중인 캠페인"

        mAdapter = BaseAdapter(ProgressViewHolder(rv_mission_more_list), this)
        getPersonalMission()
        rv_mission_more_list.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        tv_mission_more_add_campaign.setOnClickListener {
            setResult(RESULT_CODE_GO_MISSION)
            finish()
        }
    }

    private fun getPersonalMission() =
        ApiService.missionAPI.getPersonalMissionResponse(getToken())
            .map { it.data.contents }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mAdapter::setItems, this::throwError)

    companion object {
        private const val PARAMS_MISSION_TYPE = "mission_type"
        const val RESULT_CODE_GO_MISSION = 1
        fun getIntent(context: Context, missionType: CampaignList.Type): Intent {
            return Intent(context, PersonalMissionMoreActivity::class.java).apply {
                putExtra(PARAMS_MISSION_TYPE, missionType)
            }
        }
    }
}