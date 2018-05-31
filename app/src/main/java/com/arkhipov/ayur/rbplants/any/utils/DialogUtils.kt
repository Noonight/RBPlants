package com.arkhipov.ayur.rbplants.any.utils

import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.arkhipov.ayur.rbplants.R

class DialogUtils() {
    /*var positivePressed: OnPositivePressed? = null
    var negativePressed: OnNegativePressed? = null*/

    companion object {
        inline fun createOkCancel(context: Context, title: CharSequence, message: CharSequence, crossinline ok: () -> Unit, crossinline cancel: () -> Unit?): AlertDialog {
            return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        ok()
                    }
                })
                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        cancel()
                    }
                }).create()
        }

        inline fun createOkCancel(context: Context, title: Int, message: Int, crossinline ok: () -> Unit, crossinline cancel: () -> Unit?): AlertDialog {
            return createOkCancel(context, context.resources.getString(title), context.resources.getString(message), ok, cancel)
        }

        inline fun createEmailOkCancel(context: Context, title: Int, message: Int,
                                       crossinline ok: (String) -> Unit): AlertDialog {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.dialog_input_email_box, null)
            var et = view.findViewById<TextInputEditText>(R.id.et_email_dialog)
            var ti_wrapper_dialog = view.findViewById<TextInputLayout>(R.id.ti_wrapper_email_dialog)

            et.setOnClickListener {
                if (ti_wrapper_dialog.error != null)
                    ti_wrapper_dialog.error = null
            }

            val dialogBuilder = AlertDialog.Builder(context)
                .setView(view)
                .setTitle(title)
                //.setMessage(message)
                .setPositiveButton(R.string.ok) { dialog, which ->
                    if (InputFieldUtils.isEmailValid(et.text.toString())) {
                        ok(et.text.toString())
                    } else {
                        ti_wrapper_dialog.error = context.resources.getString(R.string.invalid_email_address)
                    }
                }
                .setNegativeButton(R.string.cancel, { dialog, which ->
                    dialog.cancel()
                })
            return dialogBuilder.create()
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