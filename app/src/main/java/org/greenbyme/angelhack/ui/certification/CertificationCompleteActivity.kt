package org.greenbyme.angelhack.ui.certification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        tv_complete_back.setOnClickListener {
            finish()
        }
    }

}