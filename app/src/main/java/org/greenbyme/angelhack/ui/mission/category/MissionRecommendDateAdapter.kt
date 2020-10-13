package org.greenbyme.angelhack.ui.mission.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_recommend_date.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDAO
import org.greenbyme.angelhack.utils.Utils

class MissionRecommendDateAdapter(
    private val list: List<MainMissionDAO.Content>,
    val onClickListener: OnMoreClickListener
) :
    RecyclerView.Adapter<MissionRecommendDateAdapter.Holder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mission_recommend_date, parent, false)
        return Holder(view).apply {
            missionRecommendMore.setOnClickListener {
                onClickListener.onMoreClick(list[adapterPosition].missionId)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])

    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val missionRecommendContents: TextView = view.tv_mission_recommend_date_contents
        val missionRecommendDiscription: TextView = view.tv_mission_recommend_date_complete
        val missionRecommendDate: TextView = view.tv_mission_recommend_date
        val missionRecommendCategory: TextView = view.tv_mission_recommend_category
        val missionRecommendComplete: TextView = view.tv_mission_recommend_date_complete
        val missionRecommendMore: TextView = view.bt_mission_recommend_date_yes
        val missionRecommendBackgorund: ImageView = view.img_mission_recommend_bg

        @SuppressLint("SetTextI18n")
        fun bind(item: MainMissionDAO.Content) {
            missionRecommendContents.text = item.subject
            missionRecommendDiscription.text = item.description
            missionRecommendDate.text =
                "${Utils.formatTimeMonthDayDate(item.startDate)} - ${
                    Utils.formatTimeMonthDayDate(
                        item.endDate
                    )
                }"
            missionRecommendCategory.text =
                "#${
                    Utils.getCategoryStringKOR(
                        item.category
                    )
                }"
            missionRecommendComplete.text = "${item.passCandidates}명 완료"
            Glide.with(itemView).load(item.missionPictureUrl).into(missionRecommendBackgorund)
        }
    }

    interface OnMoreClickListener {
        fun onMoreClick(mission_id: Int)
    }
}