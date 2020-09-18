package com.mykuya.android.ui.base.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.mykuya.android.ui.base.dialog.LoadingDialog
import com.mykuya.android.ui.base.presenter.IBasePresenter

abstract class BaseActivity<P : IBasePresenter> : AppCompatActivity(), IBaseView {

    private var loading : LoadingDialog? = null

    protected var mPresenter : P? = null

    abstract fun getPresenter() : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getPresenter()
        mPresenter?.onAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

    override fun showProgress(isShowing: Boolean) {
        if(isShowing) {
            loading = LoadingDialog()
            loading?.show(supportFragmentManager, null)
        } else {
            if(loading != null){
                loading?.dismiss()
                loading = null
            }
        }
    }

    fun toast(@StringRes res: Int) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}