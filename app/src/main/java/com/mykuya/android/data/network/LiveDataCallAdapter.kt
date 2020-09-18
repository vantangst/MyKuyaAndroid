package com.mykuya.android.data.network

import androidx.lifecycle.LiveData
import com.mykuya.android.model.network.Resource
import com.mykuya.android.utils.extension.toResource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<Resource<R>>>{

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): LiveData<Resource<R>> =
            object : LiveData<Resource<R>>(){
                internal var started = AtomicBoolean(false)

                override fun onActive() {
                    super.onActive()
                    if(started.compareAndSet(false, true)){
                        call.enqueue(object : Callback<R>{
                            override fun onFailure(call: Call<R>?, t: Throwable?) {
                                postValue(Resource.error(t?.message))
                            }

                            override fun onResponse(call: Call<R>?, response: Response<R>) {
                                if (response.isSuccessful) {
                                    postValue(response.toResource())
                                } else {
                                    postValue(Resource.error(response.message()))
                                }
                            }

                        })
                    }
                }
            }

}