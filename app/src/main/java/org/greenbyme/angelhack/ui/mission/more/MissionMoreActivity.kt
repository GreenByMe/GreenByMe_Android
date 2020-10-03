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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_more)
        tag = "MISSION_MORE"

        mAdapter = BaseAdapter(MissionPickHolder(rv_mission_more_list), this)

        rv_mission_more_list.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val missionType: CampaignList.Type? =
            intent.getSerializableExtra(PARAMS_MISSION_TYPE) as CampaignList.Type?

        when (missionType) {
            CampaignList.Type.POPULAR -> {
                getPopularMission()
            }
            CampaignList.Type.MY_CAMPAIGN -> {
                getPersonalMission()
            }
            null -> {
                toastMessage("잘못된 접근입니다.")
                finish()
            }
        }

    }

    private fun getPopularMission() =
        ApiService.missionAPI.getPopularMissionResponse()
            .map {
                it.data.contents
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mAdapter::setItems, this::throwError)

    private fun getPersonalMission() =
        ApiService.missionAPI.getPersonalMissionResponse(getToken())
            .map {
                it.data.contents
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mAdapter::setItems, this::throwError)

    override fun onClick(view: View, position: Int) {
        val missionId =
            mAdapter.getItem(position).missionId
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