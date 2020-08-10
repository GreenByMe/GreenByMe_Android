package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.category.MissionCategorySelectFragment

private const val ARG_PARAM1 = "mission"

class MissionFragment : Fragment(), TagOnClickListener {
    private var param1: Int? = 0

    override fun onClickTag(category: Int) {
        (activity as MainActivity).addFragment(
            MissionCategorySelectFragment.newInstance(category)
        )
    }

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

    private fun getMissionList(): Disposable =
        ApiService.missionAPI.getAllMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                rv_mission_recommend?.apply {
                    adapter = MissionRecommendAdapter(it.content)
                    layoutManager = LinearLayoutManager(context)
                }
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
}
