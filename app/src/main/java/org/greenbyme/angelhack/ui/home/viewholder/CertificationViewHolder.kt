package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList

class CertificationViewHolder(view: View) : HomeViewHolder<CertificationList>(view) {

    private val mAdapter = HomeAdapter()

    init {
        view.findViewById<RecyclerView>(R.id.rv_home_certification_img).run {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.HORIZONTAL, false)
        }
    }

//    override fun bind(data: CertificationList) {
//        if (data.isVisibleTitle) {
//            itemView.tv_certification_title.visibility = View.VISIBLE
//        } else {
//            itemView.rv_home_certification_img.layoutManager =
//                GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
//            itemView.tv_certification_title.visibility = View.GONE
//        }
//        mAdapter.setItems(data.certificationList)
//    }
}