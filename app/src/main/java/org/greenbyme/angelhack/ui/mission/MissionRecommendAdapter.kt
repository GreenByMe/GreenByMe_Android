package org.greenbyme.angelhack.ui.mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_mission_recommend.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionDAO

class MissionRecommendAdapter(private val list: ArrayList<MissionDAO>) :
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
        holder.missionRecommendContents.text = list[position].missionContents
        holder.missionRecommendDiscription.text = list[position].missionDescription
        holder.missionRecommendBackgound.setBackgroundColor(15)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val missionRecommendContents: TextView = view.tv_mission_recommend_contents
        val missionRecommendDiscription: TextView = view.tv_mission_recommend_description
        val missionRecommendBackgound: ImageView = view.img_mission_recommend_bg

    }
}