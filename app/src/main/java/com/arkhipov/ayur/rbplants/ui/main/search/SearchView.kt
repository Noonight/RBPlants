package com.arkhipov.ayur.rbplants.ui.main.search

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView
import com.pchmn.materialchips.model.ChipInterface

interface SearchView : MvpView {

    fun putFilterableChipData(filterableList: List<ChipInterface>)

    fun getSelectedChipData(): List<ChipInterface>

    fun onChipAdded(chip: ChipInterface, size: Int)
    fun onChipRemoved(chip: ChipInterface, size: Int)

}