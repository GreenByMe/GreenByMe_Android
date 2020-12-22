package org.greenbyme.angelhack.ui.certification

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.ui.certification.model.CertificationItems
import org.greenbyme.angelhack.ui.certification.viewholder.CertificationViewHolder

class CertificationAdapter : RecyclerView.Adapter<CertificationViewHolder>() {
    private val mItems = mutableListOf<CertificationItems>()

    fun getItem(index: Int): CertificationItems? {
        return mItems.getOrNull(index)
    }

    fun setItems(items: List<CertificationItems>) {
        synchronized(mItems) {
            mItems.run {
                clear()
                addAll(items)
            }

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        return CertificationViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

}