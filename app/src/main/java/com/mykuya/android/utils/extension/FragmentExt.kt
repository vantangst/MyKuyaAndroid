package com.mykuya.android.utils.extension

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(block : FragmentTransaction.() -> FragmentTransaction){
    beginTransaction().block().commit()
}

fun FragmentManager.showDialog(dialog : DialogFragment){
    dialog.show(this, dialog::class.java.simpleName)
}

fun Fragment.arguments(block : Bundle.() -> Unit) : Fragment {
    val bundle = Bundle()
    bundle.block()
    this.arguments = bundle
    return this
}

fun DialogFragment.arguments(block : Bundle.() -> Unit) : DialogFragment {
    val bundle = Bundle()
    bundle.block()
    this.arguments = bundle
    return this
}