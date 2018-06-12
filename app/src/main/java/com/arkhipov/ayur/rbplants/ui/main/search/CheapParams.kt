package com.arkhipov.ayur.rbplants.ui.main.search

import android.graphics.drawable.Drawable
import android.net.Uri
import com.pchmn.materialchips.model.ChipInterface

data class CheapParams(
    val id: Int = 0,
    val title: String = "",
    val subtitle: String = ""
) : ChipInterface {

    override fun getInfo(): String {
        return subtitle
    }

    override fun getAvatarDrawable(): Drawable? {
        return null
    }

    override fun getLabel(): String {
        return title
    }

    override fun getId(): Any {
        return id
    }

    override fun getAvatarUri(): Uri? {
        return null
    }

}