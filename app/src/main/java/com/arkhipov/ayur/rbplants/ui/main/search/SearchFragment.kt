package com.arkhipov.ayur.rbplants.ui.main.search

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.any.utils.SnackbarUtils
import com.pchmn.materialchips.ChipsInput
import com.pchmn.materialchips.model.ChipInterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : NavigationFragment(), SearchView {
    @Inject
    lateinit var presenter: SearchPresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_search)
            .includeToolbar()
            //.includeBottomNavigation()
            .toolbarTitleRes(R.string.search)
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
        (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(ci_input_search.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(ci_input_search.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
        //initChipView()
        presenter.onInitViews()
        initChipView()
    }

    fun initChipView() {
        ci_input_search.addChipsListener(object : ChipsInput.ChipsListener {
            override fun onChipAdded(chip: ChipInterface?, size: Int) {
                Log.d("Chip added. New size = $size. Chip = ${chip.toString()}")
                //TODO check everything
                this@SearchFragment.onChipAdded(chip!!, size)
            }

            override fun onChipRemoved(chip: ChipInterface?, size: Int) {
                Log.d("Chip removed. New size = $size")
                this@SearchFragment.onChipRemoved(chip!!, size)
            }

            override fun onTextChanged(newText: CharSequence?) {
                Log.d("Chip canged. New text = ${newText.toString()}")
            }

        })
    }


    override fun onChipAdded(chip: ChipInterface, size: Int) {
        presenter.onChipAdded(chip, size)
    }

    override fun onChipRemoved(chip: ChipInterface, size: Int) {
        presenter.onChipRemoved(chip, size)
    }

    override fun putFilterableChipData(filterableList: List<ChipInterface>) {
        ci_input_search.filterableList = filterableList
        //Log.d(ci_input_search.filterableList.toString())
    }

    override fun getSelectedChipData(): List<ChipInterface> {
        return ci_input_search.selectedChipList
    }

    @OnClick(R.id.fab_search)
    fun search() {
        SnackbarUtils.create(activity!!.main_fragment_container, "SEARCH ....").show()
    }
}