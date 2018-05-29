package com.arkhipov.ayur.rbplants.ui.main.profile

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
}
