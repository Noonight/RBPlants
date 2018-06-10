package com.arkhipov.ayur.rbplants.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.ui.main.MainActivity
import com.arkhipov.ayur.rbplants.ui.main.profile.ProfilePresenter
import javax.inject.Inject

class EditProfileFragment : NavigationFragment(), EditProfileView {
    @Inject
    lateinit var presenter: EditProfilePresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_edit_profile)
            .includeToolbar()
            //.includeBottomNavigation()
            .toolbarTitleRes(R.string.edit_profile)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //(activity!! as MainActivity).hideBottomNavigation()
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
}