package org.greenbyme.angelhack.ui.mission.userpick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionDAO

class MissionUserpickAdapter(private val list: ArrayList<MissionDAO>) :
    RecyclerView.Adapter<MissionUserpickAdapter.Holder>() {

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
                .inflate(R.layout.item_mission_userpick, parent, false)
        with(Holder(view)) {
            itemView.setOnClickListener {

            }
            return this
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.missionUserPickContents.text = list[position].missionContents
        holder.missionUserPickDiscription.text = list[position].missionDescription
        holder.missionUserPickIcon.setBackgroundColor(15)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val missionUserPickContents: TextView = view.tv_mission_userpick_title
        val missionUserPickDiscription: TextView = view.tv_mission_userpick_category
        val missionUserPickIcon: ImageView = view.img_mission_userpick_icon

    }
}