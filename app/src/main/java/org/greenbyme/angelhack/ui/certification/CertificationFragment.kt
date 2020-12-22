package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import android.util.Log
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
import org.greenbyme.angelhack.ui.certification.model.CertificationItems
import org.greenbyme.angelhack.ui.certification.viewmodel.CertificationViewModel

class CertificationFragment : Fragment() {
    private val mCertAdapter = CertificationAdapter()
    private var mCampaign: CertificationItems? = null
    private val mCertViewModel: CertificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_certification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setViewModel()
        loadData()
    }

    private fun initViews() {
        Log.d("certifrag", "initviews")
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
                    requireActivity(),
                    mCampaign?.subject ?: "",
                    mCampaign?.personalMissionId ?: -1,
                    mCampaign?.category ?: ""
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("certifrag", "destroy")
    }

    private fun setViewModel() {

        Log.d("certifrag", "setmodel")
        mCertViewModel.run {
            certData.observe(viewLifecycleOwner, Observer {
                mCertAdapter.setItems(it)
                mCertAdapter.notifyDataSetChanged()
                indicator_certification.createIndicators(it.size, 0)
            })

            // TODO : 백키누르면 계속 set됨
            showNoMissionFragment.observe(viewLifecycleOwner, Observer {
                if (it.peekContent()) {
                    (activity as MainActivity).setFragment(CertificationNoItemFragment())
                }
            })
        }
    }

    private fun loadData() {

        Log.d("certifrag", "loaddata")
        mCertViewModel.loadCertData((requireActivity() as BaseActivity).getToken())
    }
}