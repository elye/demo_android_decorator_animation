package com.elyeproj.animatedecorator

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.recycler_view

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyViewAdapter(this)
        recycler_view.addItemDecoration(AnimatedDecorator(
                ViewAnimatedDecoratorDrawable(recycler_view.setupChildView(R.layout.decorator_twin_fish)), AnimatedDecorator.Side.TOP) {
            it == recycler_view.layoutManager.itemCount - 1
        })
        recycler_view.addItemDecoration(AnimatedDecorator(
                ViewAnimatedDecoratorDrawable(recycler_view.setupChildView(R.layout.decorator_circle)), AnimatedDecorator.Side.BOTTOM) {
            it == 0
        })
    }

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
