package com.arkhipov.ayur.rbplants.data

import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.arkhipov.ayur.rbplants.data.repos.FirestoreRep
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReposManager @Inject constructor(
    private val firestoreRep: FirestoreRep
) {

    fun addUser(user: UserFire, handler: ReposCallback<UserFire>) : Disposable {
        return firestoreRep.createUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<UserFire>() {
                override fun onNext(t: UserFire?) {
                }

                override fun onError(t: Throwable?) {
                }

                override fun onComplete() {
                }

            })
            /*.subscribeWith(object : DisposableSubscriber<UserFire>() {
                override fun onComplete(){}

                override fun onNext(t: UserFire?) {
                    handler.onSuccess(t as UserFire)
                }
                override fun onError(t: Throwable?) {
                    handler.onError(t as Exception)
                }
            })*/
    }

    fun getUser(uId: String, handler: ReposCallback<UserFire>) : Disposable {
        return firestoreRep.getUser(uId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<UserFire>() {
                override fun onComplete() { }

                override fun onNext(t: UserFire?) {
                    handler.onSuccess(t as UserFire)
                }
                override fun onError(t: Throwable?) {
                    handler.onError(t as Exception)
                }
            })
    }
}