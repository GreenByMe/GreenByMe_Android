package org.greenbyme.angelhack.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.BaseFragment
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.CampaignList
import org.greenbyme.angelhack.ui.home.viewmodel.HomeViewModel
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.more.MissionMoreActivity
import org.greenbyme.angelhack.ui.mission.more.PersonalMissionMoreActivity


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {
    private val mHomeViewModel: HomeViewModel by viewModels()

    private val mHomeAdapter: HomeAdapter by lazy {
        HomeAdapter().apply {
            itemClickListener = object : HomeItemClickListener() {
                override fun onCampaignClicked() {
                    startActivity(
                        PersonalMissionMoreActivity.getIntent(
                            requireContext(),
                            CampaignList.Type.MY_CAMPAIGN
                        )
                    )
                }

                override fun onPopularCampaignClicked() {
                    startActivity(
                        MissionMoreActivity.getIntent(
                            requireContext(),
                            CampaignList.Type.POPULAR
                        )
                    )
                }

                override fun onMissionClicked(missionId: Int, missionType: CampaignList.Type) {
                    startActivityForResult(
                        MissionDetailActivity.getIntent(requireContext(), missionId, missionType),
                        REQ_CAMPAIGN_DETAIL
                    )
                }
            }
        }
    }

    companion object {
        const val REQ_CAMPAIGN_DETAIL = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setViewModel()
        loadData()
    }

    private fun initViews() {
        refreshLayout.setOnRefreshListener {
            loadData()
        }

        rv_home.run {
            adapter = mHomeAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun loadData() {
        mHomeViewModel.loadUserHomeInfo((requireActivity() as BaseActivity).getToken())
    }

    private fun setViewModel() {
        mHomeViewModel.run {
            loadingData.observe(viewLifecycleOwner, Observer {
                refreshLayout.isRefreshing = it
            })

            homeData.observe(viewLifecycleOwner, Observer {
                mHomeAdapter.setItems(it)
                mHomeAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQ_CAMPAIGN_DETAIL -> {
                    loadData()
                    return
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
