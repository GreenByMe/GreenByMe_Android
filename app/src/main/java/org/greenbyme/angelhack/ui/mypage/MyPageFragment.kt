package org.greenbyme.angelhack.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.greenbyme.angelhack.R


class MyPageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmegit pgit nt_my_page, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {

            }
    }
}
