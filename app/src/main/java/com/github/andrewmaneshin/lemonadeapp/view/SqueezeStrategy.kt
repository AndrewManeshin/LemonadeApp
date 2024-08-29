package com.github.andrewmaneshin.lemonadeapp.view

import com.github.andrewmaneshin.lemonadeapp.IntCache

interface SqueezeStrategy {

    fun randomApply(flag: Boolean, block: () -> Unit)

    class Base(
        private val times: IntCache
    ) : SqueezeStrategy {

        override fun randomApply(flag: Boolean, block: () -> Unit) {
            if (flag) {
                if (times.read() == 0) {
                    block.invoke()
                    times.save((1..3).random())
                } else {
                    times.save(times.read() - 1)
                }
            } else {
                block.invoke()
            }
        }
    }

    object Test : SqueezeStrategy {
        override fun randomApply(flag: Boolean, block: () -> Unit) {
            block.invoke()
        }
    }
}