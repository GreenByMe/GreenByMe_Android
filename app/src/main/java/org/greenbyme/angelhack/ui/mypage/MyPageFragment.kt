package org.greenbyme.angelhack.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.databinding.FragmentMyPageBinding
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.mission.MissionTagAdapter
import org.greenbyme.angelhack.ui.mission.TagOnClickListener
import org.greenbyme.angelhack.ui.mypage.post.FeedPostFragment


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
        val mViewmodel = MyPageUiModel(repo = MyPageRepo(activity as BaseActivity))
//        MyPageUiModel.showPostFragment.observe(this, Observer {
//            it.getContentIfNotHandled()?.let {
//                (activity as MainActivity).addFragment(FeedPostFragment())
//            }
//        })

        binding.mypageVm = mViewmodel
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
