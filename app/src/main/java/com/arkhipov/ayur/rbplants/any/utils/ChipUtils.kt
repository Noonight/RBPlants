package com.arkhipov.ayur.rbplants.any.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.pchmn.materialchips.ChipView

class ChipUtils
{
    companion object
    {
        fun create(context: Context): ChipLocalBuilder
        {
            return ChipLocalBuilder(ChipView(context))
        }

        class ChipLocalBuilder(
            val chipView: ChipView
        )
        {
            fun label(label: String): ChipLocalBuilder
            {
                chipView.label = label
                return this
            }

            fun padding(left: Int, top: Int, right: Int, bottom: Int): ChipLocalBuilder
            {
                chipView.setPadding(left, top, right, bottom)
                return this
            }

            fun avatarIcon(boolean: Boolean): ChipLocalBuilder
            {
                chipView.setHasAvatarIcon(boolean)
                return this
            }

            fun avatarIcon(drawable: Drawable): ChipLocalBuilder
            {
                chipView.setAvatarIcon(drawable)
                return this
            }

            fun backgroundColor(color: Int): ChipLocalBuilder
            {
                chipView.setChipBackgroundColor(color)
                return this
            }

            fun labelColor(color: Int): ChipLocalBuilder
            {
                chipView.setLabelColor(color)
                return this
            }

            fun deleteIconColor(color: Int): ChipLocalBuilder
            {
                chipView.setDeleteIconColor(color)
                return this
            }

            fun deleteIcon(drawable: Drawable): ChipLocalBuilder
            {
                chipView.setDeleteIcon(drawable)
                return this
            }

            fun chipClick(func: (View) -> Unit): ChipLocalBuilder
            {
                chipView.setOnChipClicked(func)
                return this
            }

            fun chipDelete(func: (View) -> Unit): ChipLocalBuilder
            {
                chipView.setOnDeleteClicked(func)
                return this
            }

            fun build() = this
        }
    }
}