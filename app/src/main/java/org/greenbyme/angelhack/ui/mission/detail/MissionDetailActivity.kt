package org.greenbyme.angelhack.ui.mission.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.synthetic.main.activity_mission_detail.*
import kotlinx.android.synthetic.main.item_mission_detail_eco_point.*
import kotlinx.android.synthetic.main.item_mission_detail_header.*
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.databinding.ActivityMissionDetailBinding
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.utils.Utils

class MissionDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityMissionDetailBinding
    private lateinit var viewModel: MissionDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val missionId = intent.getIntExtra(PARAMS_MISSION_ID, -1)
        val missionType: CampaignList.Type? =
            intent.getSerializableExtra(PARAMS_MISSION_TYPE) as CampaignList.Type

        binding = ActivityMissionDetailBinding.inflate(layoutInflater)
        viewModel = MissionDetailViewModel(MissionDetailRepository(context), missionId)
        viewModel.mAddMission.observe(this) {
            if(it.peekContent()){
                joinSucceed()
            }
        }
        binding.missionDetailVm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        if (missionId == -1) {
            toastMessage("잘못된 접근입니다.")
            finish()
        }
    }

    fun joinSucceed() {
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
