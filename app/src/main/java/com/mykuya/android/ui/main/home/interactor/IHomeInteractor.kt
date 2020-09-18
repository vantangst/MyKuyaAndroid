package com.mykuya.android.ui.main.home.interactor

import androidx.lifecycle.Observer
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource
import com.mykuya.android.ui.base.interactor.IBaseInteractor


interface IHomeInteractor : IBaseInteractor {
    fun getHomeData()

    fun observerViewModel(callback: Observer<Resource<ResultModel<HomeDataModel>>>)

}