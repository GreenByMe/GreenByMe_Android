package org.greenbyme.angelhack.ui.mypage.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FeedPostUiModel(repo: FeedPostRepo) : ViewModel() {
    var feedPostDao: LiveData<FeedPostDao> = repo.loadFeedPost()
}