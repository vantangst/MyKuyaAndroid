package com.mykuya.android.ui.main.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mykuya.android.R
import com.mykuya.android.model.CategoryModel
import com.mykuya.android.ui.base.RecyclerAdapter
import com.mykuya.android.ui.base.RecyclerHolder
import kotlinx.android.synthetic.main.item_category.view.*

class FeatureAdapter(view: RecyclerView) : RecyclerAdapter<CategoryModel>(view) {
    var onItemClickListener: ((CategoryModel) -> Unit)? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = object :
        RecyclerHolder<CategoryModel>(p0, R.layout.item_category) {

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item!!)
            }
        }

        override fun bind(item: CategoryModel) {
            super.bind(item)
            itemView.run {
                cv_item.text = item.name
                cv_item.background = R.drawable.ic_cleaning
            }
        }
    }

}