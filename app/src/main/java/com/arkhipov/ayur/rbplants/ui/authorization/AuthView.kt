package com.arkhipov.ayur.rbplants.ui.authorization

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

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

    /**
     * Show [MainActivity]
     * */
    fun showMain()
}
