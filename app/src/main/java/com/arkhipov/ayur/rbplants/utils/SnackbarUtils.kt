package com.arkhipov.ayur.rbplants.utils

import android.support.design.widget.Snackbar
import android.view.View

class SnackbarUtils
{
    companion object
    {
        fun createAction(view: View, message: CharSequence, btnMessage: CharSequence, func: (View)-> Unit): Snackbar
        {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(btnMessage, View.OnClickListener(func))
        }

        fun createAction(view: View, message: Int, btnMessage: Int, func: (View) -> Unit): Snackbar
        {
            return createAction(view, view.resources.getString(message), view.resources.getString(btnMessage), func)
        }

        fun create(view: View, message: CharSequence) = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

        fun create(view: View, message: Int) = create(view, view.resources.getString(message))
    }

    interface Action {
        fun action(view: View)
    }
}