package org.greenbyme.angelhack.ui.mypage.post

import androidx.lifecycle.LiveData

class FeedPostUiModel(repo : FeedPostRepo) {
    var feedPostDao : LiveData<FeedPostDao>? = repo.loadFeedPost()


}