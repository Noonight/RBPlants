package com.arkhipov.ayur.rbplants.any.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.arkhipov.ayur.rbplants.R

class DialogUtils()
{
    /*var positivePressed: OnPositivePressed? = null
    var negativePressed: OnNegativePressed? = null*/

    companion object
    {
        inline fun createOkCancel(context: Context, title: CharSequence, message: CharSequence, crossinline ok: ()-> Unit, crossinline cancel: ()-> Unit?): AlertDialog
        {
            return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener
                {
                    override fun onClick(dialog: DialogInterface?, which: Int)
                    {
                        ok()
                    }
                })
                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener
                {
                    override fun onClick(dialog: DialogInterface?, which: Int)
                    {
                        cancel()
                    }
                }).create()
        }

        inline fun createOkCancel(context: Context, title: Int, message: Int, crossinline ok: ()-> Unit, crossinline cancel: ()-> Unit?): AlertDialog
        {
            return createOkCancel(context, context.resources.getString(title), context.resources.getString(message), ok, cancel)
        }
    }

    /*fun initCallbacks(positivePressed: OnPositivePressed, negativePressed: OnNegativePressed)
    {
        this.positivePressed = positivePressed
        this.negativePressed = negativePressed
    }

    fun setPositivePressedListener(action: OnPositivePressed)
    {
        this.positivePressed = action
    }

    fun setNegativePressedListener(action: OnNegativePressed)
    {
        this.negativePressed = action
    }

    interface OnPositivePressed
    {
        fun onPress()
    }

    interface OnNegativePressed
    {
        fun onPress()
    }*/
}