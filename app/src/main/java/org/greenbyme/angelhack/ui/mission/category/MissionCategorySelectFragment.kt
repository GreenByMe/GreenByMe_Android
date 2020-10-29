package org.greenbyme.angelhack.ui.mission.category

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
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.userpick.MissionUserPickFragment
import org.greenbyme.angelhack.utils.Utils

private const val ARG_PARAM1 = "mission_select"

class MissionCategorySelectFragment : Fragment(),
    TagOnClickListener,
    MissionCategorySelectContract.View,
    MissionRecommendDateAdapter.OnMoreClickListener {

    private var category: Int = 0
    private var currentDate = "DAY"

    override lateinit var presenter: MissionCategorySelectContract.Presenter

    private val spinnerListener:AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentDate =
                Utils.getDateString(
                    position
                )
            getCategoryByList(category)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(ARG_PARAM1)
        }
        presenter = MissionCategorySelectPresenter(this)
    }

    override fun onClickTag(category: Int) {
        Log.d("categoryselect",category.toString())
        getCategoryByList(category)
    }

    override fun onMoreClick(mission_id: Int) {
        context?.let {
            startActivity(MissionDetailActivity.getIntent(it, mission_id))
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
        getCategoryByList(category)
        rv_mission_select_tag_list.apply {
            adapter =
                MissionTagAdapter(
                    MissionTagAdapter.makeDummy(category),
                    this@MissionCategorySelectFragment
                )
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        sp_mission_select_date.onItemSelectedListener = spinnerListener
        tv_mission_select_more.setOnClickListener {
            (activity as MainActivity).addFragment(MissionUserPickFragment.newInstance(0))
        }
    }


    override fun setMissionList(response: ResponseBase<MainMissionDAO>?) {
        rv_mission_select?.apply {
            adapter =
                MissionRecommendDateAdapter(
                    response!!.data.contents,
                    this@MissionCategorySelectFragment
                )
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
            presenter.getALLMissionList()
        } else
            presenter.getMissionList(category, currentDate)
    }


    companion object {
        @JvmStatic
        fun newInstance(category: Int) =
            MissionCategorySelectFragment().apply {
                this.category = category
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, category)
                }
            }
    }

    override fun throwError(msg: Throwable) {
    }

    override fun toastMessage(msg: String) {
    }

    override fun getToken(): String {
        return ""
    }
}
