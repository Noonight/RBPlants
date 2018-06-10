package com.arkhipov.ayur.rbplants.ui.main.profile.my_groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import javax.inject.Inject

class MyGroupsFragment : NavigationFragment(), MyGroupsView {
    @Inject
    lateinit var presenter: MyGroupsPresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_my_groups)
            .includeToolbar()
            //.includeBottomNavigation()
            .toolbarTitleRes(R.string.my_groups)
        //.toolbarNavigationIcon()
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
}