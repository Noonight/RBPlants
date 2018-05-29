package com.arkhipov.ayur.rbplants.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.ui.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.ui.main.profile.edit.EditProfileFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_groups.MyPlantsFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_images.MyImagesFragment
import javax.inject.Inject

class ProfileFragment : NavigationFragment(), ProfileView {
    @Inject
    lateinit var presenter: ProfilePresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_profile)
            .includeToolbar()
            //.includeBottomNavigation()
            .toolbarTitleRes(R.string.profile)
            .toolbarNavigationIcon(-1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {

    }

    @OnClick(R.id.fab_my_images_profile)
    override fun onMyImagesPressed() {
        showMyImagesFragment()
    }

    @OnClick(R.id.fab_my_groups_profile)
    override fun onMyGroupsPressed() {
        showMyGroupsFragment()
    }

    @OnClick(R.id.fab_my_plants_profile)
    override fun onMyPlantsPressed() {
        showMyPlantsFragment()
    }

    @OnClick(R.id.fab_sign_out_profile)
    override fun onSignOutPressed() {
        presenter.signOut()
    }

    @OnClick(R.id.fab_edit_profile)
    override fun onEditProfilePressed() {
        showEditProfileFragment()
    }

    override fun showEditProfileFragment() {
        Log.d("show Edit profile fragment")
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, EditProfileFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyImagesFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MyImagesFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyGroupsFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MyPlantsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyPlantsFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MyPlantsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showAuthActivity() {
        val intent = Intent(activity!!, AuthActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }
}