package org.greenbyme.angelhack.ui.mission.userpick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mission_userpick.*
import kotlinx.android.synthetic.main.fragment_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener

private const val ARG_PARAM1 = "user_pick"

class MissionUserPickFragment : Fragment(), TagOnClickListener {
    private var category: Int? = null

    override fun onClickTag(category: Int) {

    }

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
        with(inflater.inflate(R.layout.fragment_mission_userpick, container, false)) {
            init()
            getMissionList()
            return this
        }
    }

    private fun View.init() {
        rv_mission_userpick_tag_list.apply {
            adapter = MissionTagAdapter(tagListener = this@MissionUserPickFragment)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getMissionList() {
        (ApiService.missionAPI.getMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showMissionList))
    }
    private fun showMissionList(items: ResponseBase<MainMissionDAO>){
        rv_mission_userpick.apply {
            adapter = MissionPickAdapter(items.data.contents)
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(category: Int) =
            MissionUserPickFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, category)
                }
            }
    }
}
