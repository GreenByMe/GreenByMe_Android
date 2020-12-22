package org.greenbyme.angelhack.ui.certification.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.extention.convert
import org.greenbyme.angelhack.ui.certification.model.CertificationItems

class CertificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val newsImg: ImageView = view.findViewById(R.id.iv_certification_mission_thumbnail)
    private val mTitle: TextView = view.findViewById(R.id.tv_certification_mission_title)
    private val mDate: TextView = view.findViewById(R.id.tv_certification_mission_date)

    @SuppressLint("SetTextI18n")
    fun bind(data: CertificationItems) {
        val fromFormat = "yyyy-MM-dd'T'HH:mm:ss"
        val toFormat = "M/d/EEE"

        if (data.pictureUrl.isNotBlank()) {
            Glide.with(itemView).load(data.pictureUrl).into(newsImg)
        }

        mTitle.text = data.subject

        mDate.text = "${data.startDate.convert(fromFormat, toFormat)}~${
        data.endDate.convert(
            fromFormat,
            toFormat
        )
        } (하루, 1회 인증)"
    }

    companion object {
        fun from(parent: ViewGroup): CertificationViewHolder {
            return CertificationViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_certification, parent, false)
            )
        }
    }
}