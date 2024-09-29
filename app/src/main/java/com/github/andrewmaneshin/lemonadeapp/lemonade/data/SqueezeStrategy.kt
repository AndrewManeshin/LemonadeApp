package com.github.andrewmaneshin.lemonadeapp.lemonade.data

import com.github.andrewmaneshin.lemonadeapp.IntCache

interface SqueezeStrategy {

    fun randomApply(isSqueeze: Boolean, block: () -> Unit)

    class Base(
        private val times: IntCache
    ) : SqueezeStrategy {

        override fun randomApply(isSqueeze: Boolean, block: () -> Unit) {
            if (isSqueeze) {
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
        override fun randomApply(isSqueeze: Boolean, block: () -> Unit) {
            block.invoke()
        }
    }
}