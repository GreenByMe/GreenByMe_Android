package org.greenbyme.angelhack.ui.mission.userpick

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.MissionDAO

class MissionPickAdapter(private val list: List<MainMissionDAO.Content>) :
    RecyclerView.Adapter<MissionPickHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionPickHolder {
        return MissionPickHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MissionPickHolder, position: Int) {
        holder.bind(list[position])
    }

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

}