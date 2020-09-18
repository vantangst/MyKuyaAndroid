package com.mykuya.android.ui.main.home

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.mykuya.android.R
import com.mykuya.android.model.CategoryModel
import com.mykuya.android.model.WhatNewModel
import com.mykuya.android.ui.base.view.BaseFragment
import com.mykuya.android.ui.main.home.interactor.HomeInteractor
import com.mykuya.android.ui.main.home.interactor.IHomeInteractor
import com.mykuya.android.ui.main.home.presenter.HomePresenter
import com.mykuya.android.ui.main.home.presenter.IHomePresenter
import com.mykuya.android.ui.main.home.view.IHomeView
import com.mykuya.android.ui.picklocation.DialogPickLocation
import kotlinx.android.synthetic.main.fragment_home.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment<IHomePresenter>(), IHomeView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val listFeature = mutableListOf<CategoryModel>()
    private val limitedFeatureCount = 6

    private var featuresAdapter: FeatureAdapter? = null
    private var featuredAdapter: FeatureAdapter? = null
    private var whatNewAdapter: WhatNewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        tv_hello.text = HtmlCompat.fromHtml(getString(R.string.lbl_good_afternoon), HtmlCompat.FROM_HTML_MODE_LEGACY)
        setUpFeaturedList()
        setUpFeaturesList()
        setUpWhatNewList()
        mPresenter?.getHomeData()

        iv_close_expanded_feature.setOnClickListener {
            if (iv_close_expanded_feature.rotation == 90f) {
                featuresAdapter?.items = getFeatureList(limitedFeatureCount)
                iv_close_expanded_feature.rotation = -90f
                featuresAdapter?.notifyDataSetChanged()
            } else {
                featuresAdapter?.items = listFeature
                iv_close_expanded_feature.rotation = 90f
                featuresAdapter?.notifyDataSetChanged()
            }
        }

        tv_my_location.setOnClickListener {
            DialogPickLocation(requireContext()).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getPresenter(): IHomePresenter {
        return HomePresenter<IHomeView, IHomeInteractor>(this, HomeInteractor(requireContext(),
            requireActivity() as AppCompatActivity
        ))
    }

    override fun onHomeDataError() {
        toast(R.string.common_error)
    }

    override fun onHomeDataSuccess() {
        toast("onHomeDataSuccess")

        listFeature.clear()
        listFeature.addAll(CategoryModel.mocks(21))
        featuresAdapter?.items = getFeatureList(limitedFeatureCount)
        featuredAdapter?.items = CategoryModel.mocks(3)
        whatNewAdapter?.items = WhatNewModel.mocks(2)
    }

    private fun setUpFeaturesList() {
        featuresAdapter = FeatureAdapter(rv_features)
        featuresAdapter?.onItemClickListener = {
            toast("click on ${it.name}")
        }
    }

    private fun getFeatureList(count: Int) : MutableList<CategoryModel> {
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
        if (isShowing) {
            toast(msg ?: getString(R.string.common_error))
        }
    }


}