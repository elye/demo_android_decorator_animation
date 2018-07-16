package com.elyeproj.animatedecorator

import android.graphics.Canvas
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewAnimatedDecoratorDrawable(parent: ViewGroup, @LayoutRes resource: Int): AnimatedDecorator.AnimatedDecoratorDrawable {

    val view: View

    init {
        view = parent.setupChildView(resource)
    }

    override fun draw(canvas: Canvas) {
        view.draw(canvas)
    }

    override val height = view.measuredHeight
    override val width = view.measuredWidth


    private fun ViewGroup.setupChildView(@LayoutRes layout: Int): View {
        val view = LayoutInflater.from(context).inflate(layout, this, false)
        view.layoutParams ?: run {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val displayMetrics = context.resources.displayMetrics
        val widthSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY)
        val childWidth = ViewGroup.getChildMeasureSpec(widthSpec, paddingLeft + paddingRight, view.layoutParams.width)
        val childHeight = ViewGroup.getChildMeasureSpec(heightSpec, paddingTop + paddingBottom, view.layoutParams.height)

        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)

        return view
    }
}
