package com.mykuya.android.ui.base.view

interface IBaseView {
    fun showProgress(isShowing : Boolean)
    fun showError(isShowing : Boolean, msg: String?)
}