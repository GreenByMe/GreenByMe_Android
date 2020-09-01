package org.greenbyme.angelhack.ui.mission.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mission_detail.*
import kotlinx.android.synthetic.main.item_mission_detail_eco_point.*
import kotlinx.android.synthetic.main.item_mission_detail_header.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.utils.Utils

class MissionDetailActivity : BaseActivity(), MissionDetailContract.View {
    override lateinit var presenter: MissionDetailContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_detail)

        presenter = MissionDetailPresenter(this)

        val missionId = intent.getIntExtra(PARAMS_MISSION_ID, -1)
        val missionType: CampaignList.Type? =
            intent.getSerializableExtra(PARAMS_MISSION_TYPE) as CampaignList.Type

        if (missionId == -1) {
            toastMessage("잘못된 접근입니다.")
        } else {
            when (missionType) {
                CampaignList.Type.POPULAR -> {
                    presenter.getMissionDetail(missionId)
                }
                CampaignList.Type.MY_CAMPAIGN -> {
                    presenter.getMissionProgressDetail(missionId)
                }
                null -> {
                    toastMessage("잘못된 접근입니다.")
                }
            }
        }
    }

    // TODO : MVVM update
    override fun setMissionDetail(item: MissionDetailDAO) {
        val missionDetailBackGround: ImageView = img_mission_detail_bg
        val missionDetailContents: TextView = tv_mission_recommend_date_contents
        val missionDetailDiscription: TextView = tv_mission_detail_description
        val missionDetailDate: TextView = tv_mission_recommend_date
        val missionDetailCategory: TextView = tv_mission_recommend_category
        val missionDetailComplete: TextView = tv_mission_recommend_date_complete
        val missionDetailYes: TextView = bt_mission_recommend_date_yes
        val missionDetailPlantTree: TextView = tv_mission_detail_plant_tree
        val missionDetailCarbon: TextView = tv_mission_detail_carbon

        missionDetailContents.text = item.subject
        missionDetailDiscription.text = Html.fromHtml(item.description)
        missionDetailDate.text =
            "${Utils.formatTimeMonthDayDate(item.startDate)} - ${Utils.formatTimeMonthDayDate(item.endDate)}"
        missionDetailCategory.text =
            "#${Utils.getCategoryStringKOR(item.category)}"
        missionDetailComplete.text = "${item.passCandidatesCount}명 완료"

        missionDetailPlantTree.text = "${String.format("%.2f", item.expectTree)}그루"
        missionDetailCarbon.text = "${String.format("%.2f", item.expectCo2)}KgCO2"
        Picasso.get().load(item.pictureUrl).into(missionDetailBackGround)

        missionDetailYes.setOnClickListener {
            presenter.addMission(item)
            setResult(RESULT_OK)
        }
    }

    override fun joinSucessed() {
        Toast.makeText(this, "미션에 참가하였습니다.", Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        const val PARAMS_MISSION_ID = "mission_id"
        const val PARAMS_MISSION_TYPE = "mission_type"
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
