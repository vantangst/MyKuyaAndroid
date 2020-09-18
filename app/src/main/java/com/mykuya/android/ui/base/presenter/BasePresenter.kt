package com.mykuya.android.ui.base.presenter

import com.mykuya.android.ui.base.interactor.IBaseInteractor
import com.mykuya.android.ui.base.view.IBaseView

open class BasePresenter<V : IBaseView, I : IBaseInteractor>
        constructor(protected var view : V?, protected var interactor : I?) : IBasePresenter {

    override fun onAttach() {
    }

    override fun onDetach() {
        interactor?.onDestroy()

        view = null
        interactor = null
    }

}