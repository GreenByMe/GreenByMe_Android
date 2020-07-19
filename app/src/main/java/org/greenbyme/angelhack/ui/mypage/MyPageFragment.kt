package org.greenbyme.angelhack.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.certification.CertificationCompleteActivity
import org.greenbyme.angelhack.ui.home.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem
import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener


class MyPageFragment : Fragment(), TagOnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_mypage_edit_btn.setOnClickListener {
            val intent = Intent(context, CertificationCompleteActivity::class.java)
            activity?.startActivity(intent)
        }

        rv_mypage_tag_list.apply {
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(), this@MyPageFragment)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        rv_mypage_user_photo.apply {
            val mData = ArrayList<HomeItem>()
            mData.run {
                add(
                    CertificationList(
                        "title", listOf(
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png"),
                            CertificationListItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png")
                        ), false
                    )
                )
            }
            val mHomeAdapter = HomeAdapter()
            mHomeAdapter.setItems(mData)
            adapter = mHomeAdapter
            layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
        }
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {

            }
    }

    override fun onClickTag(category: Int) {

    }
}
