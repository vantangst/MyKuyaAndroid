package com.mykuya.android.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.mykuya.android.R
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.model.CategoryModel
import com.mykuya.android.ui.base.view.BaseFragment
import com.mykuya.android.ui.main.home.interactor.HomeInteractor
import com.mykuya.android.ui.main.home.interactor.IHomeInteractor
import com.mykuya.android.ui.main.home.presenter.HomePresenter
import com.mykuya.android.ui.main.home.presenter.IHomePresenter
import com.mykuya.android.ui.main.home.view.IHomeView
import com.mykuya.android.ui.picklocation.DialogPickLocation
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<IHomePresenter>(), IHomeView {
    private val listFeature = mutableListOf<CategoryModel>()
    private val limitedFeatureCount = 6

    private var featuresAdapter: FeatureAdapter? = null
    private var featuredAdapter: FeatureAdapter? = null
    private var whatNewAdapter: WhatNewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        tv_hello.text = HtmlCompat.fromHtml(
            getString(R.string.lbl_good_afternoon),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        setUpFeaturedList()
        setUpFeaturesList()
        setUpWhatNewList()
        showProgress(true)
        mPresenter?.getHomeData()

        iv_close_expanded_feature.setOnClickListener {
            if (iv_close_expanded_feature.rotation == 180f) {
                featuresAdapter?.items = getFeatureList(limitedFeatureCount)
                iv_close_expanded_feature.rotation = 0f
                featuresAdapter?.notifyDataSetChanged()
            } else {
                featuresAdapter?.items = listFeature
                iv_close_expanded_feature.rotation = 180f
                featuresAdapter?.notifyDataSetChanged()
            }
        }

        tv_my_location.setOnClickListener {
            DialogPickLocation(requireContext()) {
                tv_my_location.text = it
            }.show()
        }
    }


    override fun getPresenter(): IHomePresenter {
        return HomePresenter<IHomeView, IHomeInteractor>(
            this, HomeInteractor(
                requireContext(),
                requireActivity() as AppCompatActivity
            )
        )
    }

    override fun onHomeDataError(msg: String?) {
        showProgress(false)
        toast(msg ?: getString(R.string.common_error))
    }

    override fun onHomeDataSuccess(data: HomeDataModel) {
        showProgress(false)
        listFeature.clear()
        listFeature.addAll(data.features)
        featuresAdapter?.items = getFeatureList(limitedFeatureCount)
        featuredAdapter?.items = data.featured.toMutableList()
        whatNewAdapter?.items = data.what_new.toMutableList()
    }

    private fun setUpFeaturesList() {
        featuresAdapter = FeatureAdapter(rv_features)
        featuresAdapter?.onItemClickListener = {
            toast("click on ${it.name}")
        }
    }

    private fun getFeatureList(count: Int): MutableList<CategoryModel> {
        val list = ArrayList(listFeature)
        return if (list.size > count) {
            list.subList(0, count)
        } else {
            list
        }
    }

    private fun setUpFeaturedList() {
        featuredAdapter = FeatureAdapter(rv_featured)
        featuredAdapter?.onItemClickListener = {
            toast("click on ${it.name}")
        }
    }

    private fun setUpWhatNewList() {
        whatNewAdapter = WhatNewAdapter(rv_what_new)
        whatNewAdapter?.onItemClickListener = {
            toast("click on ${it.title}")
        }
    }

    override fun showProgress(isShowing: Boolean) {
        baseActivity.showProgress(isShowing)
    }

    override fun showError(isShowing: Boolean, msg: String?) {
        showProgress(false)
        if (isShowing) {
            toast(msg ?: getString(R.string.common_error))
        }
    }
}