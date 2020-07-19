package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_mission_select.*
import kotlinx.android.synthetic.main.fragment_mission_select.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "tag"

class MissionSelectFragment : Fragment(), TagOnClickListener, AdapterView.OnItemSelectedListener {
    private var category: Int? = null
    private var currentDate = "DAY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(ARG_PARAM1)
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
                MissionTagAdapter(MissionTagAdapter.makeDummy(category!!), this@MissionSelectFragment)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        getCategoryByList(category!!)
        sp_mission_select_date.onItemSelectedListener = this@MissionSelectFragment
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentDate = MissionFragment.getDateString(position)
        getCategoryByList(category!!)
    }


    fun getMissionList(category: Int) {
        val response: Call<MainMissionDAO> =
            ApiService.networkMission.getMissionResponse(
                MissionFragment.getCategoryString(category),
                currentDate
            )
        response.enqueue(object : Callback<MainMissionDAO> {
            override fun onFailure(call: Call<MainMissionDAO>, t: Throwable) {
                Log.e("FRAG_MISSION", t.toString())
            }

            override fun onResponse(
                call: Call<MainMissionDAO>,
                response: Response<MainMissionDAO>
            ) {
                if (response.isSuccessful) {
                    setMissionList(response.body())
                }
            }
        })
    }

    fun getALLMissionList() {
        val response: Call<MainMissionDAO> =
            ApiService.networkMission.getMissionResponse()
        response.enqueue(object : Callback<MainMissionDAO> {
            override fun onFailure(call: Call<MainMissionDAO>, t: Throwable) {
                Log.e("FRAG_MISSION", t.toString())
            }

            override fun onResponse(
                call: Call<MainMissionDAO>,
                response: Response<MainMissionDAO>
            ) {
                if (response.isSuccessful) {
                    setMissionList(response.body())
                }
            }
        })
    }

    private fun setMissionList(response: MainMissionDAO?) {
        rv_mission_select.apply {
            adapter = MissionRecommendDateAdapter(response!!.content)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            setPageTransformer(MarginPageTransformer(32))
            offscreenPageLimit = 6
            setPadding(200, 0, 200, 0)
        }
    }

    private fun getCategoryByList(category: Int) {
        if (category == 0) {
            getALLMissionList()
        } else
            getMissionList(category)
    }

    override fun onClickTag(category: Int) {
        getCategoryByList(category)
    }


    companion object {
        @JvmStatic
        fun newInstance(category: Int) =
            MissionSelectFragment().apply {
                this.category = category
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, category)
                }
            }
    }
}
