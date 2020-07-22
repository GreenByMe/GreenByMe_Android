package org.greenbyme.angelhack.ui.mission

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mission_select.*
import kotlinx.android.synthetic.main.fragment_mission_select.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.userpick.MissionUserFickFragment

private const val ARG_PARAM1 = "mission_select"

class MissionSelectFragment : Fragment(), TagOnClickListener,
    MissionRecommendDateAdapter.OnMoreClickListener,
    AdapterView.OnItemSelectedListener {
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
                MissionTagAdapter(
                    MissionTagAdapter.makeDummy(category!!),
                    this@MissionSelectFragment
                )
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        getCategoryByList(category!!)
        sp_mission_select_date.onItemSelectedListener = this@MissionSelectFragment

        tv_mission_select_more.setOnClickListener {
            (activity as MainActivity).addFragment(MissionUserFickFragment.newInstance(""))
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentDate = MissionFragment.getDateString(position)
        getCategoryByList(category!!)
    }


    fun getMissionList(category: Int) =
        ApiService.networkMission
            .getMissionResponse(MissionFragment.getCategoryString(category), currentDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setMissionList)


    fun getALLMissionList() =
        ApiService.networkMission
            .getMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setMissionList)


    private fun setMissionList(response: MainMissionDAO?) {

        rv_mission_select?.apply {
            adapter = MissionRecommendDateAdapter(response!!.content, this@MissionSelectFragment)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            setPageTransformer(MarginPageTransformer(32))
            offscreenPageLimit = 6
            setPadding(160, 0, 160, 0)
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

    override fun onMoreClick(mission_id: Int) {
        val intent = Intent(context, MissionDetailActivity::class.java)
        intent.putExtra("mission_id", mission_id)
        startActivity(intent)
    }
}
