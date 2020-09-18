package com.mykuya.android.utils.extension

import com.mykuya.android.model.network.Resource
import com.google.gson.Gson
import com.mykuya.android.model.BaseModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.Error
import retrofit2.Response

fun <ResultType> Response<ResultType>.toResource() : Resource<ResultType> {
//    val error = errorBody()?.toString() ?: message()
    val error  = errorBody()?.string()?.jsonToObject(ResultModel::class.java) ?:
            BaseModel(Error(message = message()))
    return when{
        isSuccessful ->{
            val body = body()
            when{
                body != null -> Resource.success(body)
                else -> Resource.error(error.errors?.message, error as ResultType)
            }
        }else -> Resource.error(error.errors?.message, error as ResultType)
    }
}

inline fun <T> String.jsonToObject(cls: Class<T>): T{
    return Gson().fromJson(this, cls)
}

inline fun Any.objectToJson() : String = Gson().toJson(this)