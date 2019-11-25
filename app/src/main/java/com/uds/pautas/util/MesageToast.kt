package com.uds.pautas.util

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import com.uds.pautas.R

class MesageToast {

    companion object {

        fun toastShow(context: Context, text: String) {
            if(text.isEmpty()) {
                return
            }
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun textRequired(resources: Resources,valor: String): String {
            if(valor.isEmpty()) {
                return ""
            }
            return resources.getString(R.string.text_required, valor)
        }

        fun textSuccessSingup(resources: Resources): String {
            return resources.getString(R.string.text_success_singup)
        }

        fun textErrorSingup(resources: Resources): String {
            return resources.getString(R.string.text_error_singup)
        }

        fun textErrorLogin(resources: Resources): String {
            return resources.getString(R.string.text_error_login)
        }

        fun textSuccessSendEmail(resources: Resources): String {
            return resources.getString(R.string.text_success_send_email)
        }

        fun textErrorSendEmail(resources: Resources): String {
            return resources.getString(R.string.text_error_send_email)
        }

        fun textSuccessAddSchedule(resources: Resources): String {
            return resources.getString(R.string.text_success_add_schedule)
        }

        fun textErrorAddSchedule(resources: Resources): String {
            return resources.getString(R.string.text_error_add_schedule)
        }

        fun textSuccessClosedSchedule(resources: Resources): String {
            return resources.getString(R.string.text_success_closed_schedule)
        }

        fun textErrorClosedSchedule(resources: Resources): String {
            return resources.getString(R.string.text_error_closed_schedule)
        }

        fun textSuccessReopenSchedule(resources: Resources): String {
            return resources.getString(R.string.text_success_reopen_schedule)
        }
    }

}