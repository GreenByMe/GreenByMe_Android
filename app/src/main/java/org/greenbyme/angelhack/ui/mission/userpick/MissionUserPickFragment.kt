package org.greenbyme.angelhack.ui.mission.userpick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mission.*
import kotlinx.android.synthetic.main.fragment_mission_userpick.*
import kotlinx.android.synthetic.main.fragment_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.model.ResponseBase
import org.greenbyme.angelhack.ui.home.viewholder.MissionListHolder
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener
import org.greenbyme.angelhack.utils.Utils


class MissionUserPickFragment : Fragment(), TagOnClickListener {
    private var category: Int = 0
    private var currentDate: String = "ALL"
    private val spinnerListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentDate = Utils.getDateString(position)
                getCategoryByList(category)
            }
        }

    override fun onClickTag(category: Int) {
        getCategoryByList(category)
    }

    private fun getCategoryByList(category: Int) {
        if (category == 0) {
            getMissionList()
        } else
            getMissionList(category, currentDate)
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
            return this
        }
    }

    private fun View.init() {
        rv_mission_userpick_tag_list.startAnimation(AnimationUtils.loadAnimation(context, R.anim.open_animation))
        rv_mission_userpick_tag_list.apply {
            adapter =
                MissionTagAdapter(
                    MissionTagAdapter.makeDummy(category),
                    this@MissionUserPickFragment
                )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        rv_mission_userpick.apply {
            adapter = BaseAdapter(MissionListHolder(this))
            layoutManager = LinearLayoutManager(context)
        }
        //sp_mission_userpick_date.setSelection(Adapter.NO_SELECTION, true);
        sp_mission_userpick_date.onItemSelectedListener = spinnerListener
    }

    private fun getMissionList() {
        (ApiService.missionAPI.getMissionResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showMissionList))
    }

    private fun getMissionList(category: Int, currentDate: String): Disposable =
        ApiService.missionAPI
            .getMissionResponse(
                Utils.getCategoryString(
                    category
                ), currentDate
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showMissionList)

    private fun showMissionList(items: ResponseBase<MainMissionDAO>) {
        (rv_mission_userpick.adapter as BaseAdapter<MainMissionDAO.Content>).setItems(items.data.contents)
    }

    companion object {

        private const val ARG_PARAM1 = "user_pick"

        @JvmStatic
        fun newInstance(category: Int) =
            MissionUserPickFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, category)
                }
            }
    }
}
