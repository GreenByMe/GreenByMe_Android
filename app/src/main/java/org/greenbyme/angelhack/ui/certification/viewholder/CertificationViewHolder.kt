package org.greenbyme.angelhack.ui.certification.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.extention.convert
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

class CertificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mPicasso = Picasso.get()
    private val newsImg: ImageView = view.findViewById(R.id.iv_certification_mission_thumbnail)
    private val mTitle: TextView = view.findViewById(R.id.tv_certification_mission_title)
    private val mDate: TextView = view.findViewById(R.id.tv_certification_mission_date)

    @SuppressLint("SetTextI18n")
    fun bind(data: ProgressCampaign) {
        val fromFormat = "yyyy-MM-dd'T'HH:mm:ss"
        val toFormat = "M/d/EEE"

        if (data.pictureUrl.isNotBlank()) {
            mPicasso.load(data.pictureUrl).into(newsImg)
        }

        mTitle.text = data.subject ?: ""

        mDate.text = "${data.startDate.convert(fromFormat, toFormat)}~${data.endDate.convert(
            fromFormat,
            toFormat
        )} (하루, 1회 인증)"
    }
}