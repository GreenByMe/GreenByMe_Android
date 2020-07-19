package org.greenbyme.angelhack.ui.mission

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mission_recommend.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDAO
import org.greenbyme.angelhack.utils.Utils

class MissionRecommendAdapter(private val list: List<MainMissionDAO.Content>) :
    RecyclerView.Adapter<MissionRecommendAdapter.Holder>() {

    companion object {
        fun makeDummy(): ArrayList<MissionDAO> {
            val dummy = ArrayList<MissionDAO>()
            dummy.add(MissionDAO())
            dummy.add(MissionDAO())
            dummy.add(MissionDAO())
            dummy.add(MissionDAO())
            dummy.add(MissionDAO())
            dummy.add(MissionDAO())
            return dummy
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mission_recommend, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val missionRecommendContents: TextView = view.tv_mission_recommend_contents
        private val missionRecommendDiscription: TextView = view.tv_mission_recommend_description
        private val missionRecommendBackgound: ImageView = view.img_mission_recommend_bg
        private val missionRecommendDate: TextView = view.tv_mission_recommend_date

        private val missionRecommendUserCount: TextView = view.tv_mission_recommend_user_count

        fun bind(item: MainMissionDAO.Content) {
            Picasso.get().load(item.pictureUrl).into(missionRecommendBackgound)
            missionRecommendContents.text = Html.fromHtml(item.subject)
            missionRecommendDiscription.text = Html.fromHtml(item.description)
            missionRecommendDate.text = Utils.formatTimeMonthDay(item.startDate)
            missionRecommendUserCount.text="${item.passCandidates}명 완료"

        }
    }
}