package com.mykuya.android.model


open class BaseModel(val errors: Error? = null)

class Error(val type : String? = null, val code : Int? = null, val message : String? = null)

class ResultModel<T>(var data : T? = null, errors : Error? = null) : BaseModel(errors)

class ResultModels<T>(var data : List<T>? = null, errors : Error? = null) : BaseModel(errors)

class ResultPageModels<T>(var total_items: Int, var data : List<T>? = null, errors : Error? = null) : BaseModel(errors)



