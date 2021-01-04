package org.greenbyme.angelhack.ui.mission.more

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
    private val onSelectedMissionState: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }
    }

    override fun onClick(view: View, position: Int) {
        val missionId = mAdapter.getItem(position).missionId
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

        sp_mission_more_type.onItemSelectedListener = onSelectedMissionState
        rv_mission_more_list.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        tv_mission_more_add_campaign.setOnClickListener {
            setResult(RESULT_CODE_GO_MISSION)
            finish()
        }
    }

    // TODO: 임시 로직 개선
    private fun getPersonalMission() =
        ApiService.missionAPI.getPersonalMissionResponse(getToken())
            .map {
                it.data.contents
                    .map { origin ->
                    ApiService.missionAPI.getMissionDetailResponse(origin.missionId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe({ find ->
                            origin.missionTitle = find.data.title
                            mAdapter.notifyDataSetChanged() // need position

                        }, this::throwError)
                    origin
                }
            }
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