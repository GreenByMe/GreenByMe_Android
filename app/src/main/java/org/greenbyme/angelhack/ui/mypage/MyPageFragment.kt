package org.greenbyme.angelhack.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.databinding.FragmentMyPageBinding
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.certification.CertificationCompleteActivity
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener


private const val ARG_PARAM1 = "profile_id"

class MyPageFragment : Fragment(), TagOnClickListener {
    var profile_id: Int = 0
    override fun onClickTag(category: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            profile_id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMyPageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        binding.mypageVm = MyPageUiModel(repo = MyPageRepo(activity as BaseActivity))
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_mypage_tag_list.apply {
            adapter = MissionTagAdapter(MissionTagAdapter.makeDummy(), this@MyPageFragment)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setProfile(user: MyPageDAO) {
        Picasso.get().load(user.pictureUrl).into(img_maypage_profile)
        tv_mypage_edit_btn.text = "${user.nickName}님의 활동 >"

        tv_mypage_edit_btn.setOnClickListener {
            onClickNickname(user)
        }
        tv_mypage_complete_count.text = user.passMissionCount.toString()
        tv_mypage_co2_count.text = user.expectCo2.toString()
        tv_mypage_plant_tree_count.text = user.expectTree.toString()

        val mAdapter = HomeAdapter()
        mAdapter.setItems(
            listOf(
                CertificationList(
                    "",
                    user.posts.map {
                        CertificationListItem(it.postId, it.picture)
                    }
                    , false
                )
            ))
        rv_mypage_user_photo.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }


    }

    private fun onClickNickname(user: MyPageDAO) {
        val intent = Intent(context, CertificationCompleteActivity::class.java)
        intent.putExtra("nickname", user.nickName)
        intent.putExtra("profile", user.pictureUrl)
        activity?.startActivity(intent)
    }

    companion object {
        fun newInstance(param1: Int) =
            MyPageFragment().apply {
                this.profile_id = param1
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

}
