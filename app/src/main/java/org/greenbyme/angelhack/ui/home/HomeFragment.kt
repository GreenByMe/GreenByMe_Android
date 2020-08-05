package org.greenbyme.angelhack.ui.home

import android.content.Intent
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
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.User
import org.greenbyme.angelhack.ui.mission.detail.MissionDetailActivity
import org.greenbyme.angelhack.ui.mission.more.MissionMoreActivity


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private val mHomeAdapter: HomeAdapter by lazy {
        HomeAdapter().apply {
            Log.e("asd","make adapter")
            itemClickListener = object : HomeItemClickListener {
                override fun onCampaignClicked() {
                    val intent = Intent(context, MissionMoreActivity::class.java)
                    context?.startActivity(intent)
                }

                override fun onPopularCampaignClicked() {
                    val intent = Intent(context, MissionMoreActivity::class.java)
                    context?.startActivity(intent)
                }

                override fun onMissionClicked(missionId: Int) {
                    val intent = Intent(context, MissionDetailActivity::class.java)
                    intent.putExtra("mission_id",missionId)
                    context?.startActivity(intent)
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

    private fun loadData() =
        ApiService.service.getUserHomeInfo(MainActivity.userId)
            .map {
                listOf<HomeItem>(
                    User(
                        MainActivity.userId,
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
