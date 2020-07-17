package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.ui.home.model.HomeItem

open class HomeViewHolder<T : HomeItem>(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(data: T) {
    }
}