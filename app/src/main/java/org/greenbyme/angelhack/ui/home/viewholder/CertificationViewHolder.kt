package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.item_home_certification.view.*
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.HomeAdapter

class CertificationViewHolder(view: View) : HomeViewHolder<CertificationList>(view) {

    private val mAdapter = HomeAdapter()

    init {
        view.findViewById<RecyclerView>(R.id.rv_home_certification_img).run {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.HORIZONTAL, false)
        }
    }

    override fun bind(data: CertificationList) {

        mAdapter.setItems(data.certificationList)
    }
}