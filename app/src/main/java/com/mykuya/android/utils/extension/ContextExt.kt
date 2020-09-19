package com.mykuya.android.utils.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Hide keyboard from EditText
 */
fun View.showKeyboard(value: Boolean) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (value) {
        requestFocus()
        (this as? EditText)?.setSelection(text.length)
        imm.showSoftInput(this, 0)
    } else imm.hideSoftInputFromWindow(windowToken, 0)
}

val Context.activity: Activity?
    get() = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.activity
        else -> null
    }

/**
 * Hide keyboard from context
 */
fun Context.showKeyboard(value: Boolean) {
    this.activity?.showKeyboard(value)
}

/**
 * Hide Keyboard from view on activity
 */
fun Activity.showKeyboard(value: Boolean) {
    var view = currentFocus as? EditText
    if (view == null) view = EditText(this)
    view.showKeyboard(value)
}

/**
 * Hide Keyboard from view on fragment
 */
fun androidx.fragment.app.Fragment.showKeyboard(value: Boolean) = view!!.showKeyboard(value)

fun Context.getResourceIdFrom(resourceId: String) : Int {
    return this.resources.getIdentifier(resourceId,"drawable", packageName)
}