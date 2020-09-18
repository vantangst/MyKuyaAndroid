package com.mykuya.android.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mykuya.android.R
import com.mykuya.android.ui.base.interactor.IBaseInteractor
import com.mykuya.android.ui.base.presenter.BasePresenter
import com.mykuya.android.ui.base.presenter.IBasePresenter
import com.mykuya.android.ui.base.view.BaseActivity
import com.mykuya.android.ui.base.view.IBaseView
import com.mykuya.android.ui.main.home.interactor.HomeInteractor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_action_bar.*

class MainActivity : BaseActivity<IBasePresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        tv_title.text = getString(R.string.app_name)
    }

    override fun showError(isShowing: Boolean, msg: String?) {
        toast(msg ?: getString(R.string.common_error))
    }

    private fun setupBottomNavigation() {
        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun getPresenter(): IBasePresenter {
        return BasePresenter<IBaseView, IBaseInteractor>(this, HomeInteractor(this,
            this)
        )
    }
}