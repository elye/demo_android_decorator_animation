package com.elyeproj.animatedecorator

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.SystemClock

class CustomAnimatedDecoratorDrawable(override val height: Int, override val width: Int) : AnimatedDecorator.AnimatedDecoratorDrawable {

    var lastTimeAnimated = 0L
    var deltaTime = 0L

    val rect = Rect(0, 0, width, height)
    val paint = Paint().apply { this.color = Color.BLACK }

    var currentLeft = MovingNumber(0, width, 0)
    var currentRight = MovingNumber(0, width, width)
    var currentTop = MovingNumber(0, height, 0)
    var currentBottom = MovingNumber(0, height, height)

    companion object {
        const val ANIMATE_DELAY = 1L
    }

    override fun draw(canvas: Canvas) {

        lastTimeAnimated = SystemClock.uptimeMillis()

        if (deltaTime + ANIMATE_DELAY < lastTimeAnimated) {
            deltaTime = lastTimeAnimated
            rect.set(currentLeft.value, currentTop.value, currentRight.value, currentBottom.value)
        }
        canvas.drawRect(rect, paint)
    }

    class MovingNumber(val start: Int, val end: Int, var current: Int) {
        enum class Direction {
            FORWARD,
            BACKWARD
        }

        private var direction =
                if (Math.abs(current - start) > Math.abs(current - end))
                    Direction.FORWARD
                else
                    Direction.BACKWARD

        val value : Int
        get() {
            when (direction) {
                Direction.FORWARD -> {
                    current++
                }
                Direction.BACKWARD -> {
                    current--
                }
            }

            if (current > end) {
                direction = Direction.BACKWARD
                current = end
            } else if (current < start) {
                direction = Direction.FORWARD
                current = start
            }

            return current
        }
    }
}