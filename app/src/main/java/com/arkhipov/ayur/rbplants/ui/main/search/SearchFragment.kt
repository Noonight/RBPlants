package com.arkhipov.ayur.rbplants.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.utils.SnackbarUtils
import javax.inject.Inject

class SearchFragment : NavigationFragment(), SearchView
{
    @Inject
    lateinit var presenter: SearchPresenter

    companion object
    {
        fun newInstance(bundle: Bundle): SearchFragment
        {
            val mInstance = SearchFragment()
            mInstance.arguments = bundle
            return mInstance
        }
    }

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>>
    {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_search)
            .includeToolbar()
            .includeBottomNavigation()
            .toolbarTitleRes(R.string.search)
            .toolbarNavigationIcon(-1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews()
    {
        SnackbarUtils.create(view!!, "Hello! searchFragment").show()
    }
}