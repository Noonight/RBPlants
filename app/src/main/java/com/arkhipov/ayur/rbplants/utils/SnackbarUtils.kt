package com.arkhipov.ayur.rbplants.utils

import android.support.design.widget.Snackbar
import android.view.View

class SnackbarUtils
{
    companion object
    {
        fun create(view: View, message: CharSequence, btnMessage: CharSequence, func: (View)-> Unit): Snackbar
        {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(btnMessage, View.OnClickListener(func))
        }

        fun create(view: View, message: Int, btnMessage: Int, func: (View) -> Unit): Snackbar
        {
            return create(view, view.resources.getString(message), view.resources.getString(btnMessage), func)
        }
    }

    interface Action {
        fun action(view: View)
    }
}