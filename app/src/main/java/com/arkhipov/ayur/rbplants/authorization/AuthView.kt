package com.arkhipov.ayur.rbplants.authorization

import com.arkhipov.ayur.rbplants.base.mvp.MvpView
import com.google.firebase.auth.FirebaseUser

interface AuthView : MvpView
{
    /**
     * Показывает Steppers(шаги) "Wizzard light"
     * */
    fun showSteppersGuide()

    /**
     * Проверка авторизованного пользователя
     * */
    fun checkAuthUser()

    /**
     * Show [SignInFragment]
     * */
    fun showSignIn()

/*    *//**
     * Update UI depending on user arg
     * *//*
    fun updateUI(user: FirebaseUser)*/

    /**
     * Show [MainActivity]
     * */
    fun showMain()
}
