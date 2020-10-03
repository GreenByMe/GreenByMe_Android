package org.greenbyme.angelhack.ui.mypage.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity

class FeedPostRepo(val activity: BaseActivity, private val postId: Int) {
    fun loadFeedPost(): LiveData<FeedPostDao> {
        MutableLiveData<FeedPostDao>().let { ret ->
            ApiService.postAPI.getPosts(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ret.value = it.data }, activity::throwError)
            return ret
        }
    }

}