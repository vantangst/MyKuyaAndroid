package com.mykuya.android.model

import com.mykuya.android.utils.extension.duplicate

data class WhatNewModel(val id: Int, val title: String, val description: String, val image: String) {
    companion object {
        fun mocks(count: Int) = WhatNewModel(1, "How to use the app", "Getting access to on-demand...", "").duplicate(count)
    }
}