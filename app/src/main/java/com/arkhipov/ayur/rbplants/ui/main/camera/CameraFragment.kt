package com.arkhipov.ayur.rbplants.ui.main.camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.*
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import butterknife.ButterKnife
import butterknife.OnClick
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.menu.MenuAction
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.menu.MenuActions
import com.arkhipov.ayur.rbplants.any.utils.DialogUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.ui.google_sheets.RequestPermissionsToolImpl
import com.arkhipov.ayur.rbplants.ui.main.MainActivity
import com.arkhipov.ayur.rbplants.ui.main.camera.photo_change_tag.PhotoChangeTagFragment
import com.google.android.cameraview.AspectRatio
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

class CameraFragment : NavigationFragment(), CameraView {
    @Inject
    lateinit var presenter: CameraPresenter

    var photo: Bitmap? = null

    private var mBackgroundHandler: Handler? = null

    private var mCurrentFlash: Int = 0

    companion object {
        val REQUEST_CAMERA_RESULT = 1003
    }

    private val FLASH_OPTIONS = intArrayOf(com.google.android.cameraview.CameraView.FLASH_AUTO, com.google.android.cameraview.CameraView.FLASH_OFF, com.google.android.cameraview.CameraView.FLASH_ON)

    private val FLASH_ICONS = intArrayOf(R.drawable.ic_flash_auto_white_24dp, R.drawable.ic_flash_off_white_24dp, R.drawable.ic_flash_on_white_24dp)

    private val FLASH_TITLES = intArrayOf(R.string.flash_auto, R.string.flash_off, R.string.flash_on)

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_camera)
            //.includeToolbar()
            //.includeBottomNavigation()
            //.toolbarTitleRes(R.string.take_a_photo)
            /*.menuRes(R.menu.menu_camera, MenuActions.Builder()
                .action(R.id.switch_flash, {
                    val item = toolbar.menu.findItem(R.id.switch_flash)
                    mCurrentFlash = (mCurrentFlash + 1) % FLASH_OPTIONS.size
                    item.setTitle(FLASH_TITLES[mCurrentFlash])
                    item.setIcon(FLASH_ICONS[mCurrentFlash])
                    cv_camera.setFlash(FLASH_OPTIONS[mCurrentFlash])
                })
                .action(R.id.switch_camera, {
                    val item = toolbar.menu.findItem(R.id.switch_camera)
                    val facing = cv_camera.facing
                    cv_camera.facing = if (facing == com.google.android.cameraview.CameraView.FACING_FRONT)
                        com.google.android.cameraview.CameraView.FACING_BACK
                    else
                        com.google.android.cameraview.CameraView.FACING_FRONT
                }))*/
    }

    /*override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_camera, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.switch_camera -> {
                mCurrentFlash = (mCurrentFlash + 1) % FLASH_OPTIONS.size
                item.setTitle(FLASH_TITLES[mCurrentFlash])
                item.setIcon(FLASH_ICONS[mCurrentFlash])
                cv_camera.setFlash(FLASH_OPTIONS[mCurrentFlash])
            }
            R.id.switch_flash -> {
                val facing = cv_camera.facing
                cv_camera.facing = if (facing == com.google.android.cameraview.CameraView.FACING_FRONT)
                    com.google.android.cameraview.CameraView.FACING_BACK
                else
                    com.google.android.cameraview.CameraView.FACING_FRONT
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity!! as MainActivity).hideBottomNavigation()
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // need before super
        //toolbar = view.findViewById(R.id.toolbar_camera)
        //setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        iv_switch_light_camera.visibility
        iv_exit_camera.visibility
        iv_switch_camera.visibility

        initCamera()
    }

    fun initCamera() {
        cv_camera.addCallback(mCallback)
        //Log.d(cv_camera.supportedAspectRatios.)
        /*cv_camera.supportedAspectRatios.forEach {
            Log.d(it.toString())
        }*/
        cv_camera.setAspectRatio(AspectRatio.of(16, 9))
        //cv_camera.setAspectRatio()
        cv_camera.start()
    }

    @OnClick(R.id.fab_take_photo_camera)
    override fun onTakePhotoFabPressed() {
        //cv_camera.takePicture()
        presenter.onTakePhotoPressed()
    }

    @OnClick(R.id.iv_exit_camera)
    override fun onExitIvPressed() {
        //activity!!.main_bottom_navigation.setCurrentItem(0)
        activity!!.onBackPressed()
        //(activity!! as MainActivity).showBottomNavigation()
        (activity!! as MainActivity).main_bottom_navigation.setCurrentItem(0, true)
    }

    @OnClick(R.id.iv_switch_camera)
    override fun onSwitchCameraIvPressed() {
        val facing = cv_camera.facing
        cv_camera.facing = if (facing == com.google.android.cameraview.CameraView.FACING_FRONT)
            com.google.android.cameraview.CameraView.FACING_BACK
        else
            com.google.android.cameraview.CameraView.FACING_FRONT
    }

    @OnClick(R.id.iv_switch_light_camera)
    override fun onSwitchFlashCameraIvPressed() {
        mCurrentFlash = (mCurrentFlash + 1) % FLASH_OPTIONS.size
        //iv_switch_light_camera.setTitle(FLASH_TITLES[mCurrentFlash])
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv_switch_light_camera.setImageDrawable(context!!.getDrawable(FLASH_ICONS[mCurrentFlash]))
        }
        cv_camera.setFlash(FLASH_OPTIONS[mCurrentFlash])
    }

    override fun takePicture() {
        cv_camera.takePicture()
    }

    private val mCallback = object : com.google.android.cameraview.CameraView.Callback() {

        override fun onCameraOpened(cameraView: com.google.android.cameraview.CameraView?) {
            Log.d("onCameraOpened")
        }

        override fun onCameraClosed(cameraView: com.google.android.cameraview.CameraView?) {
            Log.d("onCameraClosed")
        }

        override fun onPictureTaken(cameraView: com.google.android.cameraview.CameraView, data: ByteArray) {
            Log.d("onPictureTaken " + data.size)
            /*Toast.makeText(cameraView.context, R.string.picture_taken, Toast.LENGTH_SHORT)
                .show()*/
            onTakePhotoCamera(data)

        }

    }

    override fun showGiveNamePhotoDialog(data: ByteArray) {
        DialogUtils.createSingleEtOkCancel(context!!, R.string.need_input_photo_name, -1, {
            presenter.savePhoto(data, it)
            showPhotoDetail()
        }, {
            // TODO cancel
            activity!!.main_bottom_navigation.setCurrentItem(0)
            (activity!! as MainActivity).showBottomNavigation()
            (activity!! as MainActivity).main_bottom_navigation.setCurrentItem(0, true)
        }).show()
    }

    override fun showPhotoDetail() {
        activity!!.supportFragmentManager.popBackStack()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, PhotoChangeTagFragment()) //TODO change
            .addToBackStack(null)
            .commit()
    }

    override fun onTakePhotoCamera(data: ByteArray) {
        //presenter.savePhotoToCloudFirebase(data)
        getBackgroundHandler().post(Runnable {
            val file = File(activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "picture.jpg")
            var os: OutputStream? = null
            try {
                os = FileOutputStream(file)
                os!!.write(data)
                os.close()
            } catch (e: IOException) {
                Log.w("Cannot write to $file", e)
            } finally {
                if (os != null) {
                    try {
                        os.close()
                    } catch (e: IOException) {
                        // Ignore
                    }

                }
            }
        })



        showGiveNamePhotoDialog(data)
    }


    private fun getBackgroundHandler(): Handler {
        if (mBackgroundHandler == null) {
            val thread = HandlerThread("background")
            thread.start()
            mBackgroundHandler = Handler(thread.looper)
        }
        return mBackgroundHandler as Handler
    }

    override fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA_RESULT)
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("request code = $requestCode, result code = $resultCode")
        when (requestCode) {
            REQUEST_CAMERA_RESULT -> {
                //Log.d(resultCode.toString())
//                if (data != null) {
//                    //Log.d("data from camera intent is empty")
//                    if (data.extras!!.get("data") != null) {
//                        photo = data.extras!!.get("data") as Bitmap
//                        //iv_photo_camera.setImageBitmap(photo)
//                        //App.getPhotoFileHelper().writeToFile(photo)
//                    }
//                } else {
//                    Log.d("DATA NOT FOUND WHAAT??")
//                }
            }
        }
    }*/

    override fun onResume() {
        cv_camera.setAspectRatio(AspectRatio.of(16, 9))
        cv_camera.start()
        super.onResume()
    }

    override fun onPause() {
        cv_camera.stop()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler!!.getLooper().quitSafely()
            } else {
                mBackgroundHandler!!.getLooper().quit()
            }
            mBackgroundHandler = null
        }
    }
}