package com.github.andrewmaneshin.lemonadeapp.lemonade.data

import com.github.andrewmaneshin.lemonadeapp.IntCache
import com.github.andrewmaneshin.lemonadeapp.R

interface LemonadeRepository {

    fun next()
    fun drawableRes(): Int
    fun descriptionRes(): Int

    class Base(
        private val index: IntCache,
        private val squeezeStrategy: SqueezeStrategy
    ) : LemonadeRepository {

        private val listData = listOf(
            Pair(R.drawable.lemon_tree, R.string.tree_description),
            Pair(R.drawable.lemon_squeeze, R.string.squeeze_description),
            Pair(R.drawable.lemon_drink, R.string.drink_description),
            Pair(R.drawable.lemon_restart, R.string.restart_description)
        )

        override fun next() {
            val currentIndex = index.read()
            squeezeStrategy.randomApply(currentIndex == 1) {
                if (currentIndex == listData.size - 1)
                    index.save(0)
                else
                    index.save(currentIndex + 1)
            }
        }

        override fun drawableRes() = listData[index.read()].first

        override fun descriptionRes() = listData[index.read()].second
    }
}