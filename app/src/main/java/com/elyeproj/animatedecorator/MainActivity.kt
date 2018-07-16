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
                ViewAnimatedDecoratorDrawable(recycler_view, R.layout.decorator_twin_fish), AnimatedDecorator.Side.TOP) {
            it == recycler_view.layoutManager.itemCount - 1
        })
        recycler_view.addItemDecoration(AnimatedDecorator(
                ViewAnimatedDecoratorDrawable(recycler_view, R.layout.decorator_circle), AnimatedDecorator.Side.BOTTOM) {
            it == 0
        })

        
    }
}
