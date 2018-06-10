package com.arkhipov.ayur.rbplants.ui.main.profile

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.ui.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.ui.main.MainActivity
import com.arkhipov.ayur.rbplants.ui.main.profile.edit.EditProfileFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_groups.MyGroupsFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_groups.MyPlantsFragment
import com.arkhipov.ayur.rbplants.ui.main.profile.my_images.MyImagesFragment
import kotlinx.android.synthetic.main.fragment_profile.*
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
        val view = super.onCreateView(inflater, container, savedInstanceState)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        presenter.onInitView()
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
        (activity!! as MainActivity).hideBottomNavigation()
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, EditProfileFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyImagesFragment() {
        (activity!! as MainActivity).hideBottomNavigation()
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MyImagesFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyGroupsFragment() {
        (activity!! as MainActivity).hideBottomNavigation()
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MyGroupsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun showMyPlantsFragment() {
        (activity!! as MainActivity).hideBottomNavigation()
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

    override fun bindTvFullName(fullName: String) {
        tv_full_name_profile.text = fullName
    }

    override fun bindTvEmail(email: String) {
        tv_email_profile.text = email
    }

    override fun bindTvScore(score: String) {
        tv_score_profile.text = score
    }

    override fun bindIvAvatar(drawable: Drawable) {
        iv_avatar_profile.setImageDrawable(drawable)
    }
}