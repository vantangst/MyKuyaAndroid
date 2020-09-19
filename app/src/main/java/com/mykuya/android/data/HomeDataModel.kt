package com.mykuya.android.data

import com.mykuya.android.model.CategoryModel
import com.mykuya.android.model.WhatNewModel

data class HomeDataModel(val featured: List<CategoryModel>, val features: List<CategoryModel>, val what_new: List<WhatNewModel>)