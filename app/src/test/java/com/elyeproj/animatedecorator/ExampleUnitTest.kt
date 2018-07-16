package com.elyeproj.animatedecorator

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureNanoTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val sequence = generateSequence(1) { it + 1 }.take(50000000)
    val list = sequence.toList()

    @Test
    fun testingSomething() {

        println("Chaining Map Filter Sum\t\t " + measureNanoTime {
            (0 until 50000000).map { list[it] * 2 }.filter { it % 3 == 0 }.map { it * 3 }
        })
        println("Sub Normal Map Filter Sum\t " + measureNanoTime {
            (0 until 50000000).forEach {
                if ((list[it] * 2) % 3 == 0) {
                    list[it] * 3
                }
            }
        })
        println("Normal Map Filter Sum\t " + measureNanoTime {
            for (i in 0 until 50000000) {
                if ((list[i] * 2) % 3 == 0) {
                    list[i] * 3
                }
            }
        })
    }
}
