package com.elyeproj.animatedecorator

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class AnimatedDecorator(val drawable: AnimatedDecoratorDrawable, val side: Side, private val predicate: (Int) -> Boolean) : RecyclerView.ItemDecoration() {

    enum class Side {
        TOP,
        BOTTOM
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)
        if (predicate(position)) {
            when (side) {
                Side.TOP -> outRect.set(0, drawable.height, 0, 0)
                Side.BOTTOM -> outRect.set(0, 0, 0, drawable.height)
            }
        }
    }

    override fun onDraw(canvas: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        var mustInvalidate = false
        if (parent != null && parent.childCount > 0) {
            with(parent) {
                for (i in 0..childCount) {
                    val child = getChildAt(i)
                    val position = getChildAdapterPosition(child)
                    if (position != RecyclerView.NO_POSITION && predicate(position)) {
                        mustInvalidate = true
                        drawView(canvas, drawable, child)
                    }
                }
                if (mustInvalidate) invalidate()
            }
        }
    }

    private fun drawView(canvas: Canvas?, drawable: AnimatedDecoratorDrawable, child: View) {
        canvas?.apply {
            val params = child.layoutParams as RecyclerView.LayoutParams
            save()
            when (side) {
                Side.TOP -> translate(child.left.toFloat(), (child.top - params.topMargin - drawable.height).toFloat())
                Side.BOTTOM -> translate(child.left.toFloat(), (child.bottom + params.bottomMargin).toFloat())
            }
            drawable.draw(canvas)
            restore()
        }
    }

    interface AnimatedDecoratorDrawable {
        fun draw(canvas: Canvas)
        val height: Int
    }
}
