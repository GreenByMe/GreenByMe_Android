package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_certification_no_item.view.*
import org.greenbyme.angelhack.R

class CertificationNoItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_certification_no_item, container, false)
        return view.apply {
            tv_certification_no_item_move.setOnClickListener {
                //TODO : 이동하기 미션리스트
            }
        }
    }
}