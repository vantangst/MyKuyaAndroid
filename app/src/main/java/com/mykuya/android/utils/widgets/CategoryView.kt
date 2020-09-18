package com.mykuya.android.utils.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.mykuya.android.R
import com.mykuya.android.utils.extension.setContentView
import com.mykuya.android.utils.extension.showKeyboard
import kotlinx.android.synthetic.main.view_category.view.*

class CategoryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    var text: String
        get() = ""
        set(value) {
            tv_category_name.text = value
        }

    var background: Int
        get() = 0
        set(value) {
            iv_category.setImageResource(value)
        }

    init {
        setContentView(R.layout.view_category)
        loadAndroidAttrs(attrs, defStyleAttr)
    }

    @SuppressLint("ResourceType")
    private fun loadAndroidAttrs(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        val typed =
            context.obtainStyledAttributes(attrs, R.styleable.ViewCategory, defStyleAttr, 0)
        iv_category.setImageResource(
            typed.getResourceId(
                R.styleable.ViewCategory_android_background,
                0
            )
        )
        tv_category_name.text = typed.getString(R.styleable.ViewCategory_android_text)
        tv_category_name.setTextColor(
            typed.getColor(
                R.styleable.ViewCategory_android_textColor,
                Color.BLACK
            )
        )
        setBackgroundResource(0)
        typed.recycle()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this.setOnClickListener {
            l?.onClick(it)
            context.showKeyboard(false)
        }
    }

}