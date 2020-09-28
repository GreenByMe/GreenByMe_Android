package org.greenbyme.angelhack.ui.certification

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.ui.certification.viewholder.CertificationViewHolder
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

class CertificationAdapter : RecyclerView.Adapter<CertificationViewHolder>() {
    private val mItems = mutableListOf<ProgressCampaign>()

    fun getItem(index: Int): ProgressCampaign? {
        return mItems.getOrNull(index)
    }

    fun setItems(items: List<ProgressCampaign>) {
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