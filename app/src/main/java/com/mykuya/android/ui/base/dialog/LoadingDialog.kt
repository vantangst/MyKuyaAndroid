package com.mykuya.android.ui.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mykuya.android.R


class LoadingDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_loading, container, false)

        dialog?.requestWindowFeature(STYLE_NO_TITLE)
        isCancelable = false
        return view
    }

}