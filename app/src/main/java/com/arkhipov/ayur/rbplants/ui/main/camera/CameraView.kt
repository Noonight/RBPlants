package com.arkhipov.ayur.rbplants.ui.main.camera

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface CameraView : MvpView {
    fun startCamera()

    fun onTakePhotoFabPressed()
    fun onExitIvPressed()
    fun onSwitchCameraIvPressed()
    fun onSwitchFlashCameraIvPressed()

    fun onTakePhotoCamera(data: ByteArray)

    fun takePicture()

    fun showGiveNamePhotoDialog(data: ByteArray)

    fun showPhotoDetail()
}
