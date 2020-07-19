package org.greenbyme.angelhack.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_progress_campaign.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.ProgressItem

class ProgressCampaignFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var mAdapter : HomeAdapter = HomeAdapter()
        var list = listOf<ProgressItem>(
            ProgressItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png","title","300명 도전중","7/13","30%",11,3),
            ProgressItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png","title","300명 도전중","7/13","30%",11,3),
            ProgressItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png","title","300명 도전중","7/13","30%",11,3),
            ProgressItem("https://i.pinimg.com/originals/05/1f/f3/051ff3fb781ff83c9b0f8a32f9922fa6.png","title","300명 도전중","7/13","30%",11,3))

        rv_progress.adapter = mAdapter
        rv_progress.layoutManager = GridLayoutManager(context,2)

        mAdapter.setItems(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_campaign, container, false)
    }
}