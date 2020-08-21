package org.greenbyme.angelhack.ui.certification

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_certification_input.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.network.ApiService
import org.greenbyme.angelhack.ui.BaseActivity
import org.greenbyme.angelhack.ui.MainActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CertificationInputActivity : BaseActivity() {
    companion object {
        private const val EXTRA_THUMBNAIL = "extraThumbnail"
        private const val EXTRA_TIME = "extraTime"
        private const val EXTRA_MISSION_ID = "extraMissionId"

        fun getIntent(activity: Activity, imageUri: String, time: Long, missionId: Int): Intent {
            return Intent(activity, CertificationInputActivity::class.java).apply {
                putExtra(EXTRA_THUMBNAIL, imageUri)
                putExtra(EXTRA_TIME, time)
                putExtra(EXTRA_MISSION_ID, missionId)
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
                Log.d("certiinput", thumbnailUri)
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
                onClickUpload()
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onClickUpload(): Boolean {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("업로드 중입니다. 잠시만 기다려주세요")
        progressDialog.show()

        var json = JsonObject()

        json.addProperty("missionInfoId", intent.getIntExtra(EXTRA_MISSION_ID, 0))
        json.addProperty("open", cb_certification_open.isChecked)
        json.addProperty("text", et_certification_input.text.toString())
        json.addProperty("title", et_certification_input.text.toString())
        json.addProperty("userId", MainActivity.userId)

        var contents = json.toString().toRequestBody()
        val thumbnailUri = intent.getStringExtra(EXTRA_THUMBNAIL) ?: ""
        //val input = applicationContext.contentResolver.openInputStream(Uri.parse(thumbnailUri))
//
        val fileName = "green.png"
        //val realFile = File(Environment.getExternalStorageDirectory(), fileName)

        //File(intent.getStringExtra(EXTRA_THUMBNAIL)).let { file ->
        val realFile = File(thumbnailUri)

        Log.e("asdasd", realFile.name)
        Log.e("asdasd", realFile.toString())
        realFile.let { file ->
            val surveyBody = file.asRequestBody("image/*".toMediaType())
            val multipart = MultipartBody.Part.createFormData("file", file.name, surveyBody)
            ApiService.postAPI.postCertification(
                token = getToken(),
                missionInfoId = intent.getIntExtra(EXTRA_MISSION_ID, 0),
                open = cb_certification_open.isChecked,
                text = et_certification_input.text.toString(),
                title = et_certification_input.text.toString(),
                userId = MainActivity.userId,
                file = multipart
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressDialog.dismiss()
                    startActivity(Intent(this, CertificationCompleteActivity::class.java))
                    finish()
                }, this::throwError)
        }
        return true
    }

    fun uploadCertificationPost(uri: String, json: JsonObject) {

    }
}