package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.data.local.BaseRepository
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.category.MissionCategorySelectFragment
import org.greenbyme.angelhack.utils.AutoClearDisposable


class MissionFragment : Fragment(), TagOnClickListener, BaseRepository {
    override fun onClickTag(category: Int) {
        (activity as MainActivity).addFragment(
            MissionCategorySelectFragment.newInstance(category)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(inflater.inflate(R.layout.fragment_mission, container, false)) {
            rv_mission_tag_list.apply {
                adapter = MissionTagAdapter(tagListener = this@MissionFragment, isSelected = true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            getMissionList()
            return this
        }

    }

    private fun getMissionList() {
        autoClearDisposable.add(ApiService.missionAPI.getAllMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setupAdapter))
    }

    private fun setupAdapter(it: MainMissionDAO) {
        rv_mission_recommend?.apply {
            adapter = MissionAdapter(it.contents)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun observeLifeCycle() {
        viewLifecycleOwner.lifecycle.addObserver(autoClearDisposable)
    }

    override val autoClearDisposable: AutoClearDisposable
        get() = AutoClearDisposable(activity as BaseActivity, true)
}
