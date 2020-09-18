package com.mykuya.android.model

import com.mykuya.android.utils.extension.duplicate

data class CategoryModel(val id: Int, val name: String, val image: String) {
    companion object {
        fun mocks(count: Int) = CategoryModel(1, "Category name", "").duplicate(count)
    }
}