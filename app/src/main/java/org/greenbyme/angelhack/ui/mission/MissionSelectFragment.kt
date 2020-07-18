package org.greenbyme.angelhack.ui.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_mission_select.view.*
import org.greenbyme.angelhack.R

private const val ARG_PARAM1 = "tag"

class MissionSelectFragment : Fragment(), TagOnClickListener {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
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
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(), this@MissionSelectFragment)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        rv_mission_select.apply {
            adapter = MissionRecommendDateAdapter(MissionRecommendAdapter.makeDummy())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            setPageTransformer(MarginPageTransformer(32))
            offscreenPageLimit = 6
            setCurrentItem(1, false)
            setPadding(200, 0, 200, 0)
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MissionSelectFragment()
    }

    override fun onClickTag() {

    }
}
