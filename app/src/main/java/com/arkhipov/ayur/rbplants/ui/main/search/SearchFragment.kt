package com.arkhipov.ayur.rbplants.ui.main.search

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.pchmn.materialchips.ChipsInput
import com.pchmn.materialchips.model.ChipInterface
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
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        initChipView()
    }

    fun initChipView() {
        ci_input_search.addChipsListener(object : ChipsInput.ChipsListener {
            override fun onChipAdded(p0: ChipInterface?, p1: Int) {
                Log.d("Chip added. New size = $p1")
                //TODO check everything
            }

            override fun onChipRemoved(p0: ChipInterface?, p1: Int) {
                Log.d("Chip removed. New size = $p1")
            }

            override fun onTextChanged(p0: CharSequence?) {
                Log.d("Chip canged. New text = ${p0.toString()}")
            }

        })



        ci_input_search.filterableList = tmpTagData
        //ci_input_search.selectedChipList
    }

    val tmpTagData: List<ParamsCheap> = listOf(
        ParamsCheap(1, "Василистник", "Thalictrum"),
        ParamsCheap(2, "Прострел", "Pulsatilla")
    )

    data class ParamsCheap(
        val id: Int = 0,
        val name: String = "",
        val latin_name: String = ""//,
        //var avaUri: Uri? = null
    ) : ChipInterface {

        override fun getInfo(): String {
            return latin_name
        }

        override fun getAvatarDrawable(): Drawable? {
            return null
        }

        override fun getLabel(): String {
            return name
        }

        override fun getId(): Any {
            return id
        }

        override fun getAvatarUri(): Uri? {
            return null
        }

    }
}