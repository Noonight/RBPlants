package com.arkhipov.ayur.rbplants.any.utils

import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
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
            val et = view.findViewById<TextInputEditText>(R.id.et_email_dialog)
            val ti_wrapper_dialog = view.findViewById<TextInputLayout>(R.id.ti_wrapper_email_dialog)

            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (ti_wrapper_dialog.error != null)
                        ti_wrapper_dialog.error = null
                }
            })

            val dialogBuilder = AlertDialog.Builder(context)
                .setView(view)
                .setTitle(title)
                //.setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(R.string.cancel, { dialog, which ->
                    dialog.cancel()
                }).create()

            dialogBuilder.setOnShowListener {
                val okBtn = (dialogBuilder as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                okBtn.setOnClickListener {
                    if (InputFieldUtils.isEmailValid(et.text.toString())) {
                        ok(et.text.toString())
                        dialogBuilder.dismiss()
                    } else {
                        ti_wrapper_dialog.error = context.resources.getString(R.string.invalid_email_address)
                    }
                }
            }
            return dialogBuilder
        }

        inline fun createSingleEtOkCancel(context: Context, title: Int, message: Int,
                                          crossinline ok: (String) -> Unit, crossinline cancel: () -> Unit?): AlertDialog {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.dialog_input_field_box, null)
            val et = view.findViewById<EditText>(R.id.et_field_dialog)
            val ti_wrapper_dialog = view.findViewById<TextInputLayout>(R.id.ti_wrapper_field_dialog)

            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (ti_wrapper_dialog.error != null)
                        ti_wrapper_dialog.error = null
                }
            })

            val dialogBuilder = AlertDialog.Builder(context)
                .setView(view)
                .setTitle(title)
                //.setMessage(message)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(R.string.cancel, {
                    dialog, which ->
                    cancel()
                })
                .create()

            dialogBuilder.setOnShowListener {
                val okBtn = (dialogBuilder as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                okBtn.setOnClickListener {
                    if (!InputFieldUtils.isEmpty(et.text.toString())) {
                        ok(et.text.toString())
                        dialogBuilder.dismiss()
                    } else {
                        ti_wrapper_dialog.error = context.resources.getString(R.string.not_be_empty)
                    }
                }
            }

            return dialogBuilder
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