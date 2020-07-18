package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_mission.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.userpick.MissionUserFickFragment

private const val ARG_PARAM1 = "tag"

class MissionFragment : Fragment(), TagOnClickListener {
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
        with(inflater.inflate(R.layout.fragment_mission, container, false)) {
            init()
            return this
        }

    }

    private fun View.init() {
        rv_mission_tag_list.apply {
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(), this@MissionFragment)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rv_mission_recommend.apply {
            adapter = MissionRecommendAdapter(MissionRecommendAdapter.makeDummy())
            layoutManager = LinearLayoutManager(context)
        }

        tv_mission_more.setOnClickListener {
            (activity as MainActivity).setFragment(MissionUserFickFragment.newInstance(""))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MissionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onClickTag() {
        Toast.makeText(context, "왜 안 바 껴", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).setFragment(MissionSelectFragment.newInstance(""))
    }
}
