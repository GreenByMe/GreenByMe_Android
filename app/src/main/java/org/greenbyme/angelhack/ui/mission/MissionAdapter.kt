package org.greenbyme.angelhack.ui.mission

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_recommend.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.utils.Utils

class MissionAdapter(private val list: List<MainMissionDAO.Content>) :
    RecyclerView.Adapter<MissionAdapter.Holder>() {
    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mission_recommend, parent, false)
        val context = parent.context

        return Holder(view).apply {
            itemView.setOnClickListener {
                context.startActivity(
                    MissionDetailActivity.getIntent(context, list[adapterPosition].missionId)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val missionRecommendCaption: TextView = view.tv_mission_recommend_day_count
        private val missionRecommendSponser: TextView = view.tv_mission_recommend_sponser
        private val missionRecommendContents: TextView = view.tv_mission_recommend_contents
        private val missionRecommendDiscription: TextView = view.tv_mission_recommend_description
        private val missionRecommendBackgound: ImageView = view.img_mission_recommend_bg
        private val missionRecommendDate: TextView = view.tv_mission_recommend_date
        private val missionRecommendUserCount: TextView = view.tv_mission_recommend_user_count

        // TODO("하드코딩 서버와 상의 후 처리")
        fun bind(item: MainMissionDAO.Content) {
            missionRecommendCaption.text = "NEW!"
            missionRecommendSponser.text = "내가그린"

            Glide.with(itemView.context).load(item.missionPictureUrl)
                .into(missionRecommendBackgound)
            missionRecommendContents.text = Html.fromHtml(item.subject)
            missionRecommendDiscription.text = Html.fromHtml(item.description)
            missionRecommendDate.text = Utils.formatTimeMonthDay(item.startDate)
            missionRecommendUserCount.text = "${item.passCandidates}명 완료"
        }
    }
}