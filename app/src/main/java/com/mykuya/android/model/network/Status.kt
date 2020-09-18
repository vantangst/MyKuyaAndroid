package com.mykuya.android.model.network

enum class Status {

    LOADING,
    SUCCESS,
    ERROR;

    fun isSuccess() = this == SUCCESS

    fun isLoading() = this == LOADING

    fun isError() = this == ERROR
}