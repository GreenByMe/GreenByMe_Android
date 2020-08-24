package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.HomeItem

open class HomeViewHolder<T : HomeItem>(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(data: T) {
    }

    @CallSuper
    open fun bind(data: T, itemClickListener: HomeItemClickListener?) {
        bind(data)
    }
}