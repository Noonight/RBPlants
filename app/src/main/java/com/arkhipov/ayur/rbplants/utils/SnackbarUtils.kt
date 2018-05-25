package com.arkhipov.ayur.rbplants.utils

import android.support.design.widget.Snackbar
import android.view.View

class SnackbarUtils
{
    companion object
    {
        fun create(view: View, message: String, btnMessage: String, func: (View)-> Unit): Snackbar
        {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(btnMessage, View.OnClickListener(func))
        }
    }

    interface Action {
        fun action(view: View)
    }
}