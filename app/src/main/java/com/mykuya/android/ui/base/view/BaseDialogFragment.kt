package com.mykuya.android.ui.base.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.mykuya.android.ui.base.presenter.IBasePresenter

abstract class BaseDialogFragment<P : IBasePresenter> : DialogFragment(), IBaseView {
    protected var mPresenter : P? = null

    abstract fun getPresenter() : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = getPresenter()
        mPresenter?.onAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}