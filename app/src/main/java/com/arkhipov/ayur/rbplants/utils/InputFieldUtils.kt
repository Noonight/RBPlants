package com.arkhipov.ayur.rbplants.utils

import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
//import org.apache.commons.validator.routines.EmailValidator

class InputFieldUtils
{
    companion object
    {
        fun isEmpty(et: EditText): Boolean
        {
            if (TextUtils.isEmpty(et.text))
                return true
            return false
        }

        fun isEmpty(text: Editable): Boolean
        {
            if (TextUtils.isEmpty(text))
                return true
            return false
        }

        fun isEmpty(text: String): Boolean
        {
            if (TextUtils.isEmpty(text))
                return true
            return false
        }

        fun isEmailValid(email: String): Boolean
        {
            //val emailValidator = EmailValidator.getInstance()
            val emailValidator = android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            if (!isEmpty(email))
            {
                return emailValidator.matches()
            }
            return false
        }

        fun isPasswordValid(password: String): Boolean
        {
            if (!isEmpty(password))
            {
                if (password.length > 4)
                {
                    return true
                }
            }
            return false
        }
    }
}