package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "mission"

class MissionFragment : Fragment(), TagOnClickListener {
    private var param1: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
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
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(), this@MissionFragment, true)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        getMissionList()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            MissionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }

        fun getCategoryString(category: Int?): String {
            when (category) {
                1 -> return "ENERGY"
                2 -> return "DISPOSABLE"
                3 -> return "TRAFFIC"
                4 -> return "WATERWORKS"
                5 -> return "CAMPAIGN"
            }
            return "NONE"
        }

        fun getCategoryStringKOR(category: String?): String {
            when (category) {
                "ENERGY" -> return "에너지"
                "DISPOSABLE" -> return "일회용품"
                "TRAFFIC" -> return "교통"
                "WATERWORKS" -> return "수자원"
                "CAMPAIGN" -> return "캠페인"
            }
            return "전체"
        }

        fun getDateString(day: Int): String {
            when (day) {
                0 -> return "DAY"
                1 -> return "WEEK"
                2 -> return "MONTH"
            }
            return "DAY"
        }
    }

    fun getMissionList() {
        val response: Call<MainMissionDAO> =
            ApiService.networkMission.getAllMissionResponse()
        response.enqueue(object : Callback<MainMissionDAO> {
            override fun onFailure(call: Call<MainMissionDAO>, t: Throwable) {
                Log.e("FRAG_MISSION", t.toString())
            }

            override fun onResponse(
                call: Call<MainMissionDAO>,
                response: Response<MainMissionDAO>
            ) {
                if (response.isSuccessful) {
                    rv_mission_recommend?.apply {
                        adapter = MissionRecommendAdapter(response.body()!!.content)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        })
    }

    override fun onClickTag(category: Int) {
        (activity as MainActivity).addFragment(MissionSelectFragment.newInstance(category))
    }
}
