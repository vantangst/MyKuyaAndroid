package com.mykuya.android.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.mykuya.android.R

object CommonUtils {
    fun showLoadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.setCancelable(false)
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return progressDialog
    }
}