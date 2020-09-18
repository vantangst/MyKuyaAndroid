package com.mykuya.android.ui.main.home.interactor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.data.api.home.HomeViewModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource
import com.mykuya.android.ui.base.interactor.BaseInteractor

class HomeInteractor(val context: Context,val activity : AppCompatActivity) : BaseInteractor(context),
    IHomeInteractor {

    private val viewModel = ViewModelProvider(activity).get(HomeViewModel::class.java)

    override fun getHomeData() {
        viewModel.getHomeData()
    }


    override fun observerViewModel(callback: Observer<Resource<ResultModel<HomeDataModel>>>) {
        viewModel.mResult.observe(activity,callback)
    }
}