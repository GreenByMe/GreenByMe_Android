package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList

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