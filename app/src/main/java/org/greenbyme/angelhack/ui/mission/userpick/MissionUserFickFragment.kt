package org.greenbyme.angelhack.ui.mission.userpick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter

private const val ARG_PARAM1 = "tag"

class MissionUserFickFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(inflater.inflate(R.layout.fragment_mission_userpick, container, false)) {
            init()
            return this
        }

    }

    private fun View.init() {
        rv_mission_userpick_tag_list.adapter = MissionTagAdapter(MissionTagAdapter.makeDummy())
        rv_mission_userpick_tag_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_mission_userpick.adapter = MissionUserpickAdapter(MissionUserpickAdapter.makeDummy())
        rv_mission_userpick.layoutManager =
            LinearLayoutManager(context)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MissionUserFickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
