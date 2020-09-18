package com.mykuya.android.ui.main.home.presenter

import androidx.lifecycle.Observer
import com.mykuya.android.ui.main.home.interactor.IHomeInteractor
import com.mykuya.android.ui.main.home.view.IHomeView
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource
import com.mykuya.android.ui.base.presenter.BasePresenter

class HomePresenter<V: IHomeView,I: IHomeInteractor>
constructor(view:V?,interactor:I?) : BasePresenter<V, I>(view,interactor), IHomePresenter {

    init {
        interactor?.observerViewModel(Observer(this::onHomeData))
    }

    fun onHomeData(result: Resource<ResultModel<HomeDataModel>>?){
        if (result!=null){
            if (result.status.isSuccess()){
                view?.onHomeDataSuccess()
            }else{
                if (result.status.isError()){
                    view?.onHomeDataError()
                }
            }
        }else{
            view?.onHomeDataError()
        }
    }

    override fun getHomeData() {
        interactor?.getHomeData()
    }
}