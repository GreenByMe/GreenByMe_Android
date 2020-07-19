package org.greenbyme.angelhack.ui.certification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_certification_complete.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.utils.Utils


class CertificationCompleteActivity : AppCompatActivity() {

    private val REQUEST_EXTERNAL_STORAGE_CODE = 1
    var permissionCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification_complete)

        img_complete_instagram.setOnClickListener {

        }
        img_complete_twitter.setOnClickListener {
            onRequestPermission()
        }
        img_complete_instagram.setOnClickListener {
            onRequestPermission()
            Utils.shareInstagram(this, fl_complete_share.rootView)
        }

        tv_complete_back.setOnClickListener {
            finish()
        }
    }

    fun onRequestPermission() {
        val permissionReadStorage = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val permissionWriteStorage = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionReadStorage == PackageManager.PERMISSION_DENIED
            || permissionWriteStorage == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_EXTERNAL_STORAGE_CODE
            )
        } else {
            permissionCheck = true //이미 허용되어 있으므로 PASS
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE_CODE -> {
                var i = 0
                while (i < permissions.size) {
                    val permission = permissions[i]
                    val grantResult = grantResults[i]
                    if (permission == Manifest.permission.READ_EXTERNAL_STORAGE) {
                        permissionCheck = if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            true
                        } else {
                            Toast.makeText(this, "공유가 불가능합니다", Toast.LENGTH_LONG).show()
                            false
                        }
                    }
                    i++
                }
            }
        }
    }
}