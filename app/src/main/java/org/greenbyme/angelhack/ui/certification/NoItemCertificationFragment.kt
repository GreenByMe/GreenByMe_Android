package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_certification.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity
import org.greenbyme.angelhack.ui.certification.viewmodel.CertificationViewModel
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

class NoItemCertificationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_certification_no_item, container, false)
    }
}