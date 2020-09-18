package com.mykuya.android.data.api.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.data.api.CallApi
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource

class HomeViewModel : ViewModel() {
    var mResult : MediatorLiveData<Resource<ResultModel<HomeDataModel>>> = MediatorLiveData()
    var response : CallApi = CallApi()

    fun getHomeData() = response.getHomeData(mResult)

}