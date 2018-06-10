package com.arkhipov.ayur.rbplants.ui.main.camera

import android.graphics.Bitmap
import android.net.Uri
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.arkhipov.ayur.rbplants.data.model.UserFirePhotos
import com.google.api.client.util.DateTime
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.OnFailureListener
import android.R.attr.bitmap
import java.io.ByteArrayOutputStream


class CameraPresenter @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val fireCloud: FirebaseStorage,
    private val fireAuth: FirebaseAuth
) : MvpPresenter<CameraView>() {

    fun onTakePhotoPressed() {
        view.takePicture()
        //view.showGiveNamePhotoDialog()
    }

    fun savePhoto(byteArray: ByteArray, name: String) {
        savePhotoToCloudFirebase(byteArray, name)
    }

    fun savePhotoToCloudFirebase(byteArray: ByteArray, name: String) {
        val user = fireAuth.currentUser!!
        val createPath = "${user.uid}/${UserFirePhotos.getCollection()}/${name}_${Timestamp.now().seconds * 1000}"
        val path = fireCloud.reference.child(createPath)
        //${Timestamp.now().toString()}
        /*val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()*/

        val uploadTask = path.putBytes(byteArray)
        uploadTask
            .addOnFailureListener(OnFailureListener {
                // Handle unsuccessful uploads
                Log.w(it)
            })
            .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("upload photo complete")

                var downloadURL: String = ""
                    path.downloadUrl.addOnCompleteListener {
                    downloadURL = it.result.path
                        Log.d("Download url for photo = $downloadURL")
                        fireStore.collection(UserFire.getCollection())
                            .document(user.uid)
                            .collection(UserFirePhotos.getCollection())
                            .document(createPath)
                            .set(UserFirePhotos(
                                //(Timestamp.now().seconds * 1000).toString(),
                                createPath,
                                downloadURL,
                                "invalid"
                            ))
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Log.d("create firestore document complete")
                                } else {
                                    Log.w(it.exception)
                                }
                            }
                            .addOnFailureListener {
                                Log.w(it)
                            }
                }
                    .addOnFailureListener {
                        Log.w(it)
                    }


            })


    }

}
