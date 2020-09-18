package com.mykuya.android.ui.base.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.mykuya.android.ui.base.presenter.IBasePresenter

abstract class BaseFragment<P : IBasePresenter> : Fragment(), IBaseView {

    val baseActivity get() = activity as BaseActivity<*>

    protected var mPresenter : P? = null

    abstract fun getPresenter() : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

    fun toast(@StringRes res: Int) = baseActivity.toast(res)

    fun toast(text: String) = baseActivity.toast(text)
}