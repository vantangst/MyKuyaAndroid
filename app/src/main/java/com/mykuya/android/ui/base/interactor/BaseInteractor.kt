package com.mykuya.android.ui.base.interactor

import android.content.Context

open class BaseInteractor(private val context: Context) : IBaseInteractor {


    override fun onDestroy() {}
}