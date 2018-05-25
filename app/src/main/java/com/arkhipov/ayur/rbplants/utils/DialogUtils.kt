package com.arkhipov.ayur.rbplants.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.arkhipov.ayur.rbplants.R

class DialogUtils(private val context: Context)
{
    var positivePressed: OnPositivePressed? = null
    var negativePressed: OnNegativePressed? = null

    fun createAlertDialog(): AlertDialog
    {
        return AlertDialog.Builder(context)
            .setMessage(R.string.confirm_exit)
            .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener
            {
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    positivePressed?.onPress()
                }
            })
            .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener
            {
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    negativePressed?.onPress()
                }
            }).create()
    }

    /*fun onCreateDialog(savedInstanceState: Bundle?): DialogUtils
    {
        val builder = AlertDialog.Builder(App.instance as Context)
        builder.setMessage(R.string.confirm_exit)
            .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener
            {
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    positivePressed?.onPress()
                }
            })
            .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener
            {
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    negativePressed?.onPress()
                }
            })
        return builder.create()
    }*/

    fun initCallbacks(positivePressed: OnPositivePressed, negativePressed: OnNegativePressed)
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
    }
}