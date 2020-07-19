package org.greenbyme.angelhack.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.User
import org.greenbyme.angelhack.ui.mission.userpick.MissionUserFickFragment


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private val mHomeAdapter: HomeAdapter by lazy {
        HomeAdapter().apply {
            itemClickListener = object : HomeItemClickListener {
                override fun onCampaignClicked() {

                }

                override fun onPopularCampaignClicked() {
                    MissionUserFickFragment
                }
            }
        }
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
        ApiService.service.getUserHomeInfo(3)
            .map {
                listOf<HomeItem>(
                    User(
                        3,
                        it.nickName,
                        it.treeSentence,
                        it.progressCampaign,
                        it.expectedCO2,
                        it.expectedTree,
                        it.progressRates
                    ),
                    it.myCampaign,
                    it.popularCampaign
                )
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                refreshLayout.isRefreshing = true
            }
            .doFinally {
                refreshLayout.isRefreshing = false
            }
            .subscribe({
                mHomeAdapter.setItems(it)
            }, {
                Log.e("TAG_SHOW_ERR", it.toString())
            })
    }
}
