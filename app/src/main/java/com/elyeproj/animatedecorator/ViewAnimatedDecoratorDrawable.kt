package com.elyeproj.animatedecorator

import android.graphics.Canvas
import android.view.View

class ViewAnimatedDecoratorDrawable(val view: View): AnimatedDecorator.AnimatedDecoratorDrawable {
    override fun draw(canvas: Canvas) {
        view.draw(canvas)
    }

    override val height = view.measuredHeight
}
