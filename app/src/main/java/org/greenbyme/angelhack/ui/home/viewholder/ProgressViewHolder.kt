package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_personal.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.extention.loadUriWithToken
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.home.model.ProgressItem
import org.greenbyme.angelhack.utils.Utils

class ProgressViewHolder(view: View) : BaseAdapter.BaseHolder<ProgressItem.Content>(view) {

    override fun getItemResId(): Int {
        return R.layout.item_mission_personal
    }

    override fun bind(
        items: ProgressItem.Content,
        onClickListener: BaseAdapter.OnClickPositionListener?
    ) {
        with(itemView) {
            // TODO : API 업데이트후 끝난미션 안가리기
            Glide.with(itemView).loadUriWithToken(items.missionPictureUrl).into(img_mission_progress_bg)
            tv_mission_progress_day.text = Utils.formatTimeMonthDay(items.endDate)
            tv_mission_recommend_sponser.text = "${items.finishCount}명 도전중"
            tv_mission_progress_contents.text = items.missionTitle
            val missionProgress = (items.progress.toFloat() / items.finishCount.toFloat()) * 100f
            pb_mission_progress_complete.progress = missionProgress.toInt()
            tv_mission_progress_percents.text = "${missionProgress.toInt()}%"
            tv_mission_progress_percents_count.text =
                items.progress.toString() + "/" + items.finishCount.toString()
        }
    }

    override fun from(
        parent: ViewGroup,
        onClickListener: BaseAdapter.OnClickPositionListener?
    ): RecyclerView.ViewHolder {
        return ProgressViewHolder(getInflater(parent)).apply {
            itemView.setOnClickListener {
                onClickListener?.onClick(itemView, adapterPosition)
            }
        }
    }
}