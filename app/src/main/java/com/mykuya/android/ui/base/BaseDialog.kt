package com.mykuya.android.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.mykuya.android.ui.base.presenter.IBasePresenter
import com.mykuya.android.ui.base.view.IBaseView

abstract class BaseDialog<P : IBasePresenter>(context: Context) : Dialog(context), IBaseView {
    protected var mPresenter : P? = null

    abstract fun getPresenter() : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getPresenter()
        mPresenter?.onAttach()

    }


    override fun dismiss() {
        super.dismiss()
        mPresenter?.onDetach()
    }
}