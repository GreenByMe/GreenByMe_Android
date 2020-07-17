package org.greenbyme.angelhack.ui.certification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.hardware.camera2.*
import android.hardware.camera2.CameraCaptureSession.StateCallback
import android.hardware.camera2.params.StreamConfigurationMap
import android.media.Image
import android.media.ImageReader
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.util.SparseIntArray
import android.view.Surface
import android.view.TextureView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_picutre.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenbyme.angelhack.R
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class TakePictureActivity : AppCompatActivity() {

    var TAG = "TAG_YH"

    var faceCamera = true
    var cameraDevice: CameraDevice? = null

    lateinit var cameraId: String
    lateinit var texture: SurfaceTexture
    lateinit var surface: Surface
    lateinit var captureRequest: CaptureRequest
    lateinit var captureRequestBuilder: CaptureRequest.Builder
    lateinit var cameraCaptureSessions: CameraCaptureSession


    var imageDimension: Size? = null
    lateinit var imageReader: ImageReader
    lateinit var file :File
    lateinit var mBackgroundHandler: Handler
    lateinit var mBackgroundThread: HandlerThread

    val ORIENTATION : SparseIntArray = SparseIntArray()

    val REQUEST_CAMERA_PERMISSION = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picutre)
        takePicture()

        ORIENTATION.append(Surface.ROTATION_0,90)
        ORIENTATION.append(Surface.ROTATION_90,0)
        ORIENTATION.append(Surface.ROTATION_180,270)
        ORIENTATION.append(Surface.ROTATION_270,180)

        txv_certification.surfaceTextureListener = textureListener

        btn_certification_picture.setOnClickListener {
            takePicture()
        }

    }

    private var textureListener: TextureView.SurfaceTextureListener =
        object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture?,
                width: Int,
                height: Int
            ) {

            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
                return false
            }

            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture?,
                width: Int,
                height: Int
            ) {
                openCamera()
            }

        }

    // openCamera() 메서드는 TextureListener 에서 SurfaceTexture 가 사용 가능하다고 판단했을 시 실행된다
    private fun openCamera() {
        Log.e(TAG, "openCamera() : openCamera()메서드가 호출되었음")

        // 카메라의 정보를 가져와서 cameraId 와 imageDimension 에 값을 할당하고, 카메라를 열어야 하기 때문에
        // CameraManager 객체를 가져온다
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        cameraId = manager.cameraIdList[0]

        var characteristics : CameraCharacteristics = manager.getCameraCharacteristics(cameraId)

        var map : StreamConfigurationMap? = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

        imageDimension = map?.getOutputSizes(SurfaceTexture::class.java)?.get(0)

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
             ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE),101)
            return
        }

        manager.openCamera(cameraId,stateCallback,null)



//        try {
//            // CameraManager 에서 cameraIdList 의 값을 가져온다
//            // FaceCamera 값이 true 이면 전면, 아니면 후면 카메라
//            cameraId = if (faceCamera) {
//                manager.cameraIdList[1]
//            } else {
//                manager.cameraIdList[0]
//            }
//
//            val characteristics = manager.getCameraCharacteristics(cameraId!!)
//            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
//
//            // SurfaceTexture 에 사용할 Size 값을 map 에서 가져와 imageDimension 에 할당해준다
//            imageDimension = map!!.getOutputSizes<SurfaceTexture>(SurfaceTexture::class.java)[0]
//
//            // 카메라를 열기전에 카메라 권한, 쓰기 권한이 있는지 확인한다
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CAMERA
//                ) != PackageManager.PERMISSION_GRANTED // 카메라 권한없음
//                && ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) { // 쓰기권한 없음
//                // 카메라 권한이 없는 경우 권한을 요청한다
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    REQUEST_CAMERA_PERMISSION
//                )
//                return
//            }
//
//            // CameraManager.openCamera() 메서드를 이용해 인자로 넘겨준 cameraId 의 카메라를 실행한다
//            // 이때, stateCallback 은 카메라를 실행할때 호출되는 콜백메서드이며, cameraDevice 에 값을 할달해주고, 카메라 미리보기를 생성한다
//            manager.openCamera(cameraId!!, stateCallback, null)
//        } catch (e: CameraAccessException) {
//            e.printStackTrace()
//        }
    }

    // openCamera() 메서드에서 CameraManager.openCamera() 를 실행할때 인자로 넘겨주어야하는 콜백메서드
    // 카메라가 제대로 열렸으면, cameraDevice 에 값을 할당해주고, 카메라 미리보기를 생성한다
    private val stateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            Log.d(TAG, "stateCallback : onOpened")

            // MainActivity 의 cameraDevice 에 값을 할당해주고, 카메라 미리보기를 시작한다
            // 나중에 cameraDevice 리소스를 해지할때 해당 cameraDevice 객체의 참조가 필요하므로,
            // 인자로 들어온 camera 값을 전역변수 cameraDevice 에 넣어 준다
            cameraDevice = camera

            // createCameraPreview() 메서드로 카메라 미리보기를 생성해준다
            createCameraPreview()
        }

        override fun onDisconnected(camera: CameraDevice) {
            Log.d(TAG, "stateCallback : onDisconnected")

            // 연결이 해제되면 cameraDevice 를 닫아준다
            cameraDevice!!.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            Log.d(TAG, "stateCallback : onError")

            // 에러가 뜨면, cameraDevice 를 닫고, 전역변수 cameraDevice 에 null 값을 할당해 준다
            cameraDevice!!.close()
            cameraDevice = null
        }
    }



    // openCamera() 에 넘겨주는 stateCallback 에서 카메라가 제대로 연결되었으면
    // createCameraPreviewSession() 메서드를 호출해서 카메라 미리보기를 만들어준다
    private fun createCameraPreview() {
        try {
            // 캡쳐세션을 만들기 전에 프리뷰를 위한 Surface 를 준비한다
            // 레이아웃에 선언된 textureView 로부터 surfaceTexture 를 얻을 수 있다
            texture = txv_certification.surfaceTexture

            // 미리보기를 위한 Surface 기본 버퍼의 크기는 카메라 미리보기크기로 구성
            texture.setDefaultBufferSize(imageDimension!!.width, imageDimension!!.height)

            // 미리보기를 시작하기 위해 필요한 출력표면인 surface
            surface = Surface(texture)

            // 미리보기 화면을 요청하는 RequestBuilder 를 만들어준다.
            // 이 요청은 위에서 만든 surface 를 타겟으로 한다
            captureRequestBuilder =
                cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(surface)

            // 위에서 만든 surface 에 미리보기를 보여주기 위해 createCaptureSession() 메서드를 시작한다
            // createCaptureSession 의 콜백메서드를 통해 onConfigured 상태가 확인되면
            // CameraCaptureSession 을 통해 미리보기를 보여주기 시작한다
            cameraDevice!!.createCaptureSession(
                listOf(surface),
                object : StateCallback() {
                    override fun onConfigureFailed(session: CameraCaptureSession) {
                        Log.d(TAG, "Configuration change")
                    }

                    override fun onConfigured(session: CameraCaptureSession) {
                        if (cameraDevice == null) {
                            // 카메라가 이미 닫혀있는경우, 열려있지 않은 경우
                            return
                        }
                        // session 이 준비가 완료되면, 미리보기를 화면에 뿌려주기 시작한다
                        cameraCaptureSessions = session

                        captureRequestBuilder.set(
                            CaptureRequest.CONTROL_MODE,
                            CameraMetadata.CONTROL_MODE_AUTO
                        )

                        try {
                            cameraCaptureSessions.setRepeatingRequest(
                                captureRequestBuilder.build(),
                                null,
                                mBackgroundHandler
                            )
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                },
                null
            )

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    // 사진찍을 때 호출하는 메서드
    private fun takePicture() {
        try {
            val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val characteristics = manager.getCameraCharacteristics(cameraDevice!!.id)
            var jpegSizes: Array<Size>? = null
            jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!
                .getOutputSizes(ImageFormat.JPEG)

            var width = jpegSizes[0].width
            var height = jpegSizes[0].height

            imageReader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1)

            val outputSurface = ArrayList<Surface>(2)
            outputSurface.add(imageReader!!.surface)
            outputSurface.add(Surface(txv_certification!!.surfaceTexture))

            val captureBuilder =
                cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
            captureBuilder.addTarget(imageReader!!.surface)

            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

            var file =
                File(Environment.getExternalStorageDirectory().toString())
            val readerListener = object : ImageReader.OnImageAvailableListener {
                override fun onImageAvailable(reader: ImageReader?) {
                    var image: Image? = null

                    try {
                        image = imageReader!!.acquireLatestImage()

                        val buffer = image!!.planes[0].buffer
                        val bytes = ByteArray(buffer.capacity())
                        buffer.get(bytes)

                        var output: OutputStream? = null
                        try {
                            output = FileOutputStream(file)
                            output.write(bytes)
                        } finally {
                            output?.close()

                            var uri = Uri.fromFile(file)
                            Log.d(TAG, "uri 제대로 잘 바뀌었는지 확인 ${uri}")

                            // 프리뷰 이미지에 set 해줄 비트맵을 만들어준다
                            var bitmap: Bitmap = BitmapFactory.decodeFile(file.path)

                            // 비트맵 사진이 90도 돌아가있는 문제를 해결하기 위해 rotate 해준다
                            var rotateMatrix = Matrix()
                            rotateMatrix.postRotate(90F)
                            var rotatedBitmap: Bitmap = Bitmap.createBitmap(
                                bitmap,
                                0,
                                0,
                                bitmap.width,
                                bitmap.height,
                                rotateMatrix,
                                false
                            )

                        }

                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        image?.close()
                    }
                }

            }

            // imageReader 객체에 위에서 만든 readerListener 를 달아서, 이미지가 사용가능하면 사진을 저장한다
            imageReader!!.setOnImageAvailableListener(readerListener, null)

            val captureListener = object : CameraCaptureSession.CaptureCallback() {
                override fun onCaptureCompleted(
                    session: CameraCaptureSession,
                    request: CaptureRequest,
                    result: TotalCaptureResult
                ) {
                    super.onCaptureCompleted(session, request, result)
                    createCameraPreview()
                }
            }

            // outputSurface 에 위에서 만든 captureListener 를 달아, 캡쳐(사진 찍기) 해주고 나서 카메라 미리보기 세션을 재시작한다
            cameraDevice!!.createCaptureSession(
                outputSurface,
                object : StateCallback() {
                    override fun onConfigureFailed(session: CameraCaptureSession) {}

                    override fun onConfigured(session: CameraCaptureSession) {
                        try {
                            session.capture(captureBuilder.build(), captureListener, null)
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                },
                null
            )


        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()

        if(txv_certification.isAvailable)
        {
            try {
                openCamera()
            }catch (e : CameraAccessException){
                e.printStackTrace()
            }
        }
        else{
            txv_certification.surfaceTextureListener = textureListener
        }
    }


}
