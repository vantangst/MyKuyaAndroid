package com.mykuya.android.ui.main.home.view

import com.mykuya.android.ui.base.view.IBaseView

interface IHomeView : IBaseView {

    fun onHomeDataError()

    fun onHomeDataSuccess()
}