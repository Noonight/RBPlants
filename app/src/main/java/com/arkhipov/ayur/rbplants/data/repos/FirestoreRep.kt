package com.arkhipov.ayur.rbplants.data.repos

import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRep @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    var registration: ListenerRegistration? = null

    fun createUser(userFire: UserFire): Flowable<UserFire> {
        Log.d(userFire.toString())
        return Flowable.create({ subscriber: FlowableEmitter<UserFire> ->
            firestore.collection(UserFire.getCollection())
                .document(userFire.uId)
                .set(userFire)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("create user with id = ${userFire.uId} successfull")
                        subscriber.onNext(userFire)
                    } else {
                        Log.w(it.exception)
                        subscriber.onError(it.exception!!)
                    }
                }
                .addOnFailureListener {
                    Log.w(it)
                    subscriber.onError(it)
                }
        }, BackpressureStrategy.BUFFER)
    }

    fun getUser(uId: String): Flowable<UserFire> {
        return Flowable.create({ subscriber ->
            firestore.collection(UserFire.getCollection())
                .document(uId)
                .get()
                .addOnSuccessListener {
                    Log.d("get user with id = ${it.getString("uId")} successfull")
                    val user = it.toObject(UserFire::class.java)
                    subscriber.onNext(user)
                }
                .addOnFailureListener {
                    Log.w(it)
                    subscriber.onError(it)
                }
        }, BackpressureStrategy.BUFFER)
    }

    fun updateUser() {

    }
}