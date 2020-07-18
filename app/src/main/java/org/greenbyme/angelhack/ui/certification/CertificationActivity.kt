package org.greenbyme.angelhack.ui.certification

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_certification.*
import org.greenbyme.angelhack.R

class CertificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification)

        setSupportActionBar(toolbar_certification)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        iv_certification_thumbnail.setImageURI(Uri.parse(intent?.getStringExtra(EXTRA_THUMBNAIL) ?: ""))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)

    }

    companion object {
        private const val EXTRA_THUMBNAIL = "extraThumbnail"

        fun getIntent(activity: Activity, imageUri: String): Intent {
            return Intent(activity, CertificationActivity::class.java).apply {
                putExtra(EXTRA_THUMBNAIL, imageUri)
            }
        }
    }
}
