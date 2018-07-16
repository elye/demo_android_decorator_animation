package com.elyeproj.animatedecorator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.recycler_view

class MainActivity : AppCompatActivity(), ViewTreeObserver.OnGlobalLayoutListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyViewAdapter(this)

        recycler_view.addItemDecoration(AnimatedDecorator(
                ViewAnimatedDecoratorDrawable(recycler_view, R.layout.decorator_twin_fish), AnimatedDecorator.Side.TOP) {
            it == recycler_view.layoutManager.itemCount - 1
        })
        recycler_view.addItemDecoration(AnimatedDecorator(
                ViewAnimatedDecoratorDrawable(recycler_view, R.layout.decorator_circle), AnimatedDecorator.Side.BOTTOM) {
            it == 0
        })

        recycler_view.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        recycler_view.addItemDecoration(
                AnimatedDecorator(CustomAnimatedDecoratorDrawable(
                        resources.getDimension(R.dimen.custom_animated_height).toInt(), recycler_view.measuredWidth),
                        AnimatedDecorator.Side.BOTTOM,
                        {
                            it > 0 && it < recycler_view.layoutManager.itemCount - 2
                        })
        )
        recycler_view.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

}
