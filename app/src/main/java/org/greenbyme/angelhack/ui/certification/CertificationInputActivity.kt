package org.greenbyme.angelhack.ui.certification

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_certification_input.*
import org.greenbyme.angelhack.R
import java.text.SimpleDateFormat
import java.util.*

class CertificationInputActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_THUMBNAIL = "extraThumbnail"
        private const val EXTRA_TIME = "extraTime"

        fun getIntent(activity: Activity, imageUri: String, time: Long): Intent {
            return Intent(activity, CertificationInputActivity::class.java).apply {
                putExtra(EXTRA_THUMBNAIL, imageUri)
                putExtra(EXTRA_TIME, time)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification_input)

        initActionBar()
        initViews()
    }

    private fun initActionBar() {
        supportActionBar?.run {
            title = "인증"
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {
        intent?.run {
            val thumbnailUri = getStringExtra(EXTRA_THUMBNAIL) ?: ""

            if (thumbnailUri.isNotBlank()) {
                Picasso.get().load(thumbnailUri).rotate(90f)
                    .into(thumbnail)
            }

            time.text = SimpleDateFormat("yyyy.M.d (EEE) HH:mm").format(
                Date(
                    getLongExtra(
                        EXTRA_TIME,
                        System.currentTimeMillis()
                    )
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_auth, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.upload -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("업로드 중입니다. 잠시만 기다려주세요")
                progressDialog.show()

                thumbnail.postDelayed({
                    progressDialog.dismiss()
                    startActivity(Intent(this, CertificationCompleteActivity::class.java))
                    finish()
                }, 2000)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}