package com.mykuya.android.data.network

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mykuya.android.model.network.Resource

abstract class NetworkBoundResource<R> @MainThread constructor(
        private val result: MediatorLiveData<Resource<R>>
) {

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<R>>

    fun request(){
        val apiResponse = createCall()
        result.value = Resource.loading()
        result.addSource(apiResponse){
            result.removeSource(apiResponse)
            it?.apply {
                result.value = this
            }
        }
    }

}