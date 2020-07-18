package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_certification_complete.*
import org.greenbyme.angelhack.R

class CertificationCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification_complete)


        img_complete_instagram.setOnClickListener {

        }
        img_complete_twitter.setOnClickListener {

        }
        img_complete_instagram.setOnClickListener {

        }

        tv_complete_back.setOnClickListener{
            finish()
        }
    }

}