package org.greenbyme.angelhack.ui.mission.userpick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_mission_userpick.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.databinding.FragmentMissionUserpickBinding
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.viewholder.MissionListHolder
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.utils.AutoClearDisposable
import org.greenbyme.angelhack.utils.Utils


class MissionUserPickFragment : Fragment() {
    private var category: Int = 0
    private var currentDate: String = "ALL"
    private lateinit var viewModel: MissionPickViewModel
    private lateinit var binding: FragmentMissionUserpickBinding
    private lateinit var autoClearDisposable: AutoClearDisposable

    private var onClickPositionListener = object : BaseAdapter.OnClickPositionListener {
        override fun onClick(view: View, position: Int) {
            (rv_mission_userpick.adapter as BaseAdapter<MainMissionDAO.Content>).getItem(position).let {
                startActivity(MissionDetailActivity.getIntent(requireContext(), it.missionId))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoClearDisposable = AutoClearDisposable(activity as BaseActivity)
        lifecycle.addObserver(autoClearDisposable)
        arguments?.let {
            category = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mission_userpick, container, false)
        viewModel = MissionPickViewModel(MissionPickRepository(inflater.context))
        viewModel.updateMissionList(category, currentDate)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.missionUserPickVm = viewModel
        with(binding.root) {
            return init()
        }
    }

    private fun View.init(): View {
        val tagClick = PublishSubject.create<Int>()
        val tagListener = TagOnClickListener { category ->
            tagClick.onNext(category)
        }

        autoClearDisposable += Observable.merge(
            RxAdapterView.itemSelections(binding.spMissionUserpickDate),
            tagClick
        )
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                category = position
                currentDate = Utils.getDateString(position)
                viewModel.updateMissionList(category, currentDate)
            }

        binding.rvMissionUserpickTagList.apply {
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.open_animation))
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(category), tagListener)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvMissionUserpick.apply {
            adapter = BaseAdapter(MissionListHolder(this), onClickPositionListener)
            layoutManager = LinearLayoutManager(context)
        }
        return this
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
