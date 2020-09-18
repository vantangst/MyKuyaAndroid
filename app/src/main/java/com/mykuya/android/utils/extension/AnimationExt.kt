package com.mykuya.android.utils.extension

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.cos
import kotlin.math.sin

fun View.circleAnimation(radius: Float = 10f,
                         duration : Long = 100000,
                         repeatCount: Int = ValueAnimator.INFINITE){
    val viewX = this.x
    val viewY = this.y

    val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
    valueAnimator.addUpdateListener {
        val value = it.animatedValue as Float
        val x = viewX + cos(value) * radius
        val y = viewY + sin(value) * radius

        this.x = x
        this.y = y
    }

    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = duration
    valueAnimator.repeatCount = repeatCount
    valueAnimator.start()

}

fun View.hideShowTop(duration : Long = 300,top : Float){

    val valueAnimator = ValueAnimator.ofFloat(0f, top)
    valueAnimator.addUpdateListener {
        val value = it.animatedValue as Float

        this.translationY = value
    }

    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = duration
    valueAnimator.start()

}
