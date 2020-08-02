package org.greenbyme.angelhack.ui.certification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_picutre.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenbyme.angelhack.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class TakePictureActivity : AppCompatActivity() {
    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null

    companion object {
        private const val REQUEST_CAMERA = 1001
        private const val EXTRA_SUBJECT = "extraKeySubject"

        fun getIntent(activity: Activity, subject: String): Intent {
            return Intent(activity, TakePictureActivity::class.java).apply {
                putExtra(EXTRA_SUBJECT, subject)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picutre)

        initActionBar()
        initViews()
    }

    private fun initActionBar() {
        supportActionBar?.run {
            title = "인증"
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initViews() {
        subject.text = "인증미션 : ${intent?.getStringExtra(EXTRA_SUBJECT) ?: ""}"

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
        } else {
            safeCameraOpen()
        }

        takePicture.setOnClickListener {
            mCamera?.takePicture(null, null, Camera.PictureCallback { data, camera ->
                lifecycleScope.launch {
                    getOutputMediaFile(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)?.let { pictureFile ->
                        try {
                            withContext(Dispatchers.IO) {
                                val fos = FileOutputStream(pictureFile)
                                fos.write(data)
                                fos.close()
                            }

                            val uri = Uri.fromFile(pictureFile)

                            startActivity(
                                CertificationInputActivity.getIntent(
                                    this@TakePictureActivity,
                                    uri.toString(), System.currentTimeMillis()
                                )
                            )
                        } catch (e: Exception) {
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("SimpleDateFormat")
    private suspend fun getOutputMediaFile(type: Int): File? = withContext(Dispatchers.IO) {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    private fun safeCameraOpen() {
        try {
            releaseCameraAndPreview()
            Camera.open().let { camera ->
                mCamera = camera

                camera_preview.addView(CameraPreview(this@TakePictureActivity, camera).also {
                    mPreview = it
                })

                mPreview?.setCamera(camera)
            }
        } catch (e: Exception) {
            finish()
        }
    }

    private fun releaseCameraAndPreview() {
        mPreview?.setCamera(null)

        mCamera?.also { camera ->
            camera.release()
            mCamera = null
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CAMERA -> {
                if (grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED) {
                    safeCameraOpen()
                } else {
                    finish()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseCameraAndPreview()
    }
}

@SuppressLint("ViewConstructor")
class CameraPreview(
    context: Context,
    private var mCamera: Camera?
) : SurfaceView(context), SurfaceHolder.Callback {

    private val mHolder: SurfaceHolder = holder.apply {
        addCallback(this@CameraPreview)
        setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }


    fun setCamera(camera: Camera?) {
        if (mCamera == camera) {
            return
        }

        stopPreviewAndFreeCamera()

        mCamera = camera

        mCamera?.run {
            try {
                setPreviewDisplay(holder)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            startPreview()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mCamera?.run {
            val params = parameters
            params.focusMode = Camera.Parameters.FOCUS_MODE_AUTO
            parameters = params

            setDisplayOrientation(90)
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mCamera?.stopPreview()
    }

    private fun stopPreviewAndFreeCamera() {
        mCamera?.apply {
            stopPreview()
            release()
            mCamera = null
        }
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
        if (mHolder.surface == null) {
            return
        }

        try {

            mCamera?.stopPreview()
        } catch (e: Exception) {
        }


        mCamera?.run {
            try {
                setPreviewDisplay(mHolder)
                startPreview()
            } catch (e: Exception) {
            }
        }
    }
}
