package com.arkhipov.ayur.rbplants.any.utils

import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import java.util.regex.Pattern

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
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            val matcher = pattern.matcher(email);
            if (matcher.matches())
            {
                return true
            }
            return false;
            //val emailValidator = EmailValidator.getInstance()
            /*val emailValidator = android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            if (!isEmpty(email))
            {
                return emailValidator.matches()
            }*/
            //return false
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