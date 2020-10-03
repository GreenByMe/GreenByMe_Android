package org.greenbyme.angelhack.ui.mypage.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.databinding.FragmentFeedPostBinding
import org.greenbyme.angelhack.ui.BaseActivity


class FeedPostFragment : Fragment() {
    var postId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFeedPostBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_feed_post, container, false)
        val mViewModel = FeedPostUiModel(repo = FeedPostRepo(activity as BaseActivity, postId))

        binding.feedpostVm = mViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        fun getInstance(postId: Int): FeedPostFragment {
            return FeedPostFragment().apply {
                this.postId = postId
            }
        }
    }
}