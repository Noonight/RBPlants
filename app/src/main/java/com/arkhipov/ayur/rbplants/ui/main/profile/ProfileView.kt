package com.arkhipov.ayur.rbplants.ui.main.profile

import android.graphics.drawable.Drawable
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface ProfileView : MvpView {
    fun showAuthActivity()

    fun onMyImagesPressed()
    fun onMyGroupsPressed()
    fun onMyPlantsPressed()
    fun onSignOutPressed()
    fun onEditProfilePressed()


    fun showEditProfileFragment()
    fun showMyImagesFragment()
    fun showMyGroupsFragment()
    fun showMyPlantsFragment()

    fun bindTvFullName(fullName: String)
    fun bindTvEmail(email: String)
    fun bindTvScore(score: String)
    fun bindIvAvatar(drawable: Drawable)
}
