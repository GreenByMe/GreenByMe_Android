package org.greenbyme.angelhack.ui.mission.detail

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mission_detail.*
import kotlinx.android.synthetic.main.item_mission_detail_eco_point.*
import kotlinx.android.synthetic.main.item_mission_detail_header.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionDetailDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.MissionFragment
import org.greenbyme.angelhack.utils.Utils

class MissionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_detail)
        val missionId = intent.getIntExtra("mission_id", -1)
        if (missionId != -1) {
            getMissionDetail(missionId)
        } else {
            Toast.makeText(applicationContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun setMissionDetail(item: MissionDetailDAO) {
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
            "#${MissionFragment.getCategoryStringKOR(item.category)}"
        missionDetailComplete.text = "${item.passCandidatesCount}명 완료"

        missionDetailPlantTree.text = "${String.format("%.2f", item.expectTree)}그루"
        missionDetailCarbon.text = "${String.format("%.2f", item.expectCo2)}KgCO2"
        Picasso.get().load(item.pictureUrl).into(missionDetailBackGround)

        missionDetailYes.setOnClickListener {
            addMission(item)
        }
    }


    private fun joinSucces() {
        Toast.makeText(this, "미션에 참가하였습니다.", Toast.LENGTH_LONG).show()
        finish()
    }


    /*
-------------------------------------------------
비지니스
-------------------------------------------------
 */

    private fun getMissionDetail(mission_id: Int): Disposable? {
        return ApiService.networkMission.getMissionDetailResponse(mission_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setMissionDetail)
    }


    private fun addMission(item: MissionDetailDAO) {
        ApiService.networkMission.PostJoinMissionResponse(MainActivity.id, item.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { joinSucces() }
    }
}
