package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_mission_select.*
import kotlinx.android.synthetic.main.fragment_mission_select.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDTO
import org.greenbyme.angelhack.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "tag"

class MissionSelectFragment : Fragment(), TagOnClickListener {
    private var param1: Int? = null

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
        with(inflater.inflate(R.layout.fragment_mission_select, container, false)) {
            init()
            return this
        }

    }

    private fun View.init() {
        rv_mission_select_tag_list.apply {
            adapter =
                MissionTagAdapter(MissionTagAdapter.makeDummy(param1!!), this@MissionSelectFragment)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        getMissionList(param1!!)
    }


    companion object {
        @JvmStatic
        fun newInstance(category: Int) =
            MissionSelectFragment().apply {
                param1 = category
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, category)
                }
            }
    }

    fun getMissionList(category: Int) {
        val response: Call<MainMissionDTO> =
            ApiService.networkMission.getMissionResponse(MissionFragment.getCategoryString(category))
        response.enqueue(object : Callback<MainMissionDTO> {
            override fun onFailure(call: Call<MainMissionDTO>, t: Throwable) {
                Log.e("FRAG_MISSION", t.toString())
            }

            override fun onResponse(
                call: Call<MainMissionDTO>,
                response: Response<MainMissionDTO>
            ) {
                if (response.isSuccessful) {
                    rv_mission_select.apply {
                        adapter = MissionRecommendDateAdapter(response.body()!!.content)
                        orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        clipToPadding = false
                        clipChildren = false
                        setPageTransformer(MarginPageTransformer(32))
                        offscreenPageLimit = 6
                        setCurrentItem(1, false)
                        setPadding(200, 0, 200, 0)
                    }

                }
            }
        })
    }

    override fun onClickTag(category: Int) {
        getMissionList(category)
    }
}
