package org.greenbyme.angelhack.ui.mission.userpick

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission.rv_mission_recommend
import kotlinx.android.synthetic.main.fragment_mission.rv_mission_tag_list
import kotlinx.android.synthetic.main.fragment_mission.view.*
import kotlinx.android.synthetic.main.fragment_mission_userpick.view.*

import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionTagDAO
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import java.util.*

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
