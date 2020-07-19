package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_certification.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.home.model.ProgressCampaign

class CertificationFragment : Fragment() {
    private val mCertAdapter = CertificationAdapter()
    private var mCampaign: ProgressCampaign? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_certification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vp_certification.run {
            adapter = mCertAdapter
            ViewPager2.ORIENTATION_HORIZONTAL

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mCampaign = mCertAdapter.getItem(position)
                }
            })
        }

        indicator_certification.setViewPager(vp_certification)

        tv_certification_go.setOnClickListener {
            startActivity(
                TakePictureActivity.getIntent(
                    requireActivity(), mCampaign?.subject ?: ""
                )
            )
        }

        loadCertData()
    }

    private fun loadCertData() {
        ApiService.certApi.getMissionResponse(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val campaigns = it.missionInfoList
                mCertAdapter.setItems(campaigns)
                indicator_certification.createIndicators(campaigns.size, 0)
            }, {
                Log.e("CertificationFragment", it.message)
            })
    }
}