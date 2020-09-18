package com.mykuya.android.ui.main.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mykuya.android.R
import com.mykuya.android.model.WhatNewModel
import com.mykuya.android.ui.base.RecyclerAdapter
import com.mykuya.android.ui.base.RecyclerHolder
import kotlinx.android.synthetic.main.item_what_is_new.view.*
import kotlin.math.roundToInt

class WhatNewAdapter(view: RecyclerView) : RecyclerAdapter<WhatNewModel>(view) {
    var onItemClickListener: ((WhatNewModel) -> Unit)? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = object :
        RecyclerHolder<WhatNewModel>(p0, R.layout.item_what_is_new) {

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item!!)
            }
        }

        override fun bind(item: WhatNewModel) {
            super.bind(item)
            itemView.run {
                val imgWidth = context.resources.displayMetrics.widthPixels
                tv_item_title.text = item.title
                tv_item_description.text = item.description
                iv_item.setImageResource(R.drawable.img_what_new_1)
                cv_item_what_new.layoutParams.width = ((0.7*imgWidth).roundToInt())
            }
        }
    }

}