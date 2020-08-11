package org.greenbyme.angelhack.ui.mission.category

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
import kotlinx.android.synthetic.main.fragment_mission_select.*
import kotlinx.android.synthetic.main.fragment_mission_select.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.userpick.MissionUserFickFragment
import org.greenbyme.angelhack.utils.Utils

private const val ARG_PARAM1 = "mission_select"

class MissionCategorySelectFragment : Fragment(),
    TagOnClickListener,
    MissionCategorySelectContract.View,
    MissionRecommendDateAdapter.OnMoreClickListener,
    AdapterView.OnItemSelectedListener {

    override lateinit var presenter: MissionCategorySelectContract.Presenter
    override fun throwError(msg: Throwable) {
        TODO("Not yet implemented")
    }

    override fun toastMessage(msg: String) {
        TODO("Not yet implemented")
    }

    override fun getToken(): String {
        TODO("Not yet implemented")
    }

    private var category: Int = 0
    private var currentDate = "DAY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(ARG_PARAM1)
        }
        presenter = MissionCategorySelectPresenter(this)
    }

    override fun onClickTag(category: Int) {
        getCategoryByList(category)
    }

    override fun onMoreClick(mission_id: Int) {
        val intent = Intent(context, MissionDetailActivity::class.java)
        intent.putExtra("mission_id", mission_id)
        startActivity(intent)
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
        setTagAdapter()
        getCategoryByList(category)
        sp_mission_select_date.onItemSelectedListener = this@MissionCategorySelectFragment

        tv_mission_select_more.setOnClickListener {
            (activity as MainActivity).addFragment(MissionUserFickFragment.newInstance(""))
        }
    }

    private fun setTagAdapter() {
        rv_mission_select_tag_list.apply {
            adapter =
                MissionTagAdapter(
                    MissionTagAdapter.makeDummy(
                        category
                    ),
                    this@MissionCategorySelectFragment
                )
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentDate =
            Utils.getDateString(
                position
            )
        getCategoryByList(category)
    }

    override fun setMissionList(response: MainMissionDAO?) {
        rv_mission_select?.apply {
            adapter =
                MissionRecommendDateAdapter(
                    response!!.content,
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


}
