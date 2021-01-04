package org.greenbyme.angelhack.ui.mission.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_mission_detail_other_feed.*
import org.greenbyme.angelhack.databinding.ActivityMissionDetailBinding
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.model.CampaignList

class MissionDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityMissionDetailBinding
    private lateinit var viewModel: MissionDetailViewModel
    private var missionId = -1
    private var missionType: CampaignList.Type? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentExtra()
        initView()
    }

    private fun getIntentExtra() {
        missionId = intent.getIntExtra(PARAMS_MISSION_ID, -1)
        missionType = intent.getSerializableExtra(PARAMS_MISSION_TYPE) as CampaignList.Type
        if (missionId == -1) {
            toastMessage("잘못된 접근입니다.")
            finish()
        }
    }

    private fun initView() {
        viewModel = MissionDetailViewModel(MissionDetailRepository(activityContext), missionId, missionType ?: CampaignList.Type.MY_CAMPAIGN)
        viewModel.mAddMission.observe(this) {
            if (it.peekContent()) {
                joinSucceed()
            }
        }
        binding = ActivityMissionDetailBinding.inflate(layoutInflater)
        binding.missionDetailVm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        rv_mission_detail_user_photo.apply {
            adapter = BaseAdapter(SingleImageHolder(rv_mission_detail_user_photo))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun joinSucceed() {
        Toast.makeText(this, "미션에 참가하였습니다.", Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        private const val PARAMS_MISSION_ID = "mission_id"
        private const val PARAMS_MISSION_TYPE = "mission_type"

        fun getIntent(
            context: Context,
            missionId: Int,
            missionType: CampaignList.Type = CampaignList.Type.POPULAR
        ): Intent {
            val intent = Intent(context, MissionDetailActivity::class.java)
            intent.putExtra(PARAMS_MISSION_ID, missionId)
            intent.putExtra(PARAMS_MISSION_TYPE, missionType)
            return intent
        }
    }

}
