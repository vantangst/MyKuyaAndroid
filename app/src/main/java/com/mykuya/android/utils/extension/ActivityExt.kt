package com.mykuya.android.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Activity.startActivity(cls : Class<*>){
    val intent = Intent(this, cls)
    startActivity(intent)
}

fun Activity.startActivityWithExtra(cls: Class<*>, block: Intent.() -> Unit){
    val intent = Intent(this, cls)
    intent.block()
    startActivity(intent)
}

fun Activity.startActivityWithSetting(cls: Class<*>, block: Activity.() -> Unit){
    val intent = Intent(this, cls)
    startActivity(intent)
    block()
}

fun Activity.startActivityWithSetting(cls: Class<*>, requestCode: Int, block: Activity.() -> Unit){
    val intent = Intent(this, cls)
    startActivityForResult(intent, requestCode)
    block()
}


fun Activity.showSoftKeyboard() {
    showSoftKeyboard(currentFocus ?: View(this))
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}


