package com.mykuya.android.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mykuya.android.data.network.ApiService
import com.mykuya.android.data.network.NetworkBoundResource
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource

class CallApi {
    private val service : ApiService by lazy { ApiService.getApiService() }

    fun getHomeData(liveData: MediatorLiveData<Resource<ResultModel<HomeDataModel>>>)=
            object : NetworkBoundResource<ResultModel<HomeDataModel>>(liveData) {
                override fun createCall(): LiveData<Resource<ResultModel<HomeDataModel>>> =
                    service.getHomeData()
            }.request()

}