package org.greenbyme.angelhack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val mData = ArrayList<HomeItem>()
    private val mHomeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mData.run {
            add(Header("안녕하세요 김그린님"))

            add(
                CampaignList(
                    "진행중인 캠페인", listOf(
                        CampaignListItem(
                            "챌린지1", "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/11", "300명 진행중"
                        ),
                        CampaignListItem(
                            "챌린지2",
                            "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/12",
                            "100명 진행중"
                        ),
                        CampaignListItem(
                            "챌린지3",
                            "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/13",
                            "200명 진행중"
                        )
                    )
                )
            )

            add(
                CampaignList(
                    "인기 캠페인", listOf(
                        CampaignListItem(
                            "캠페인1", "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/11", "300명 진행중"
                        ),
                        CampaignListItem(
                            "캠페인2",
                            "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/12",
                            "100명 진행중"
                        ),
                        CampaignListItem(
                            "캠페인3",
                            "달성",
                            "https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png",
                            "7/13",
                            "200명 진행중"
                        )
                    )
                )
            )

            add(
                NewsList(
                    "오늘의 환경 이",
                    listOf(
                        NewsListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                        NewsListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                        NewsListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png")
                    )
                )
            )

            add(
                CertificationList("title", listOf(CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                    CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                    CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                    CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png")))
            )
        }

        mHomeAdapter.setItems(mData)
        rv_home.adapter = mHomeAdapter

        val lm = LinearLayoutManager(this.context)
        rv_home.layoutManager = lm

    }
}
