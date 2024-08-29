package com.github.andrewmaneshin.lemonadeapp

interface LemonadeRepository {

    fun next()
    fun drawableRes(): Int
    fun descriptionRes(): Int

    class Base(
        private val index: IntCache
    ) : LemonadeRepository {

        private val listData = listOf(
            Pair(R.drawable.lemon_tree, R.string.tree_description),
            Pair(R.drawable.lemon_squeeze, R.string.squeeze_description),
            Pair(R.drawable.lemon_drink, R.string.drink_description),
            Pair(R.drawable.lemon_restart, R.string.restart_description)
        )

        override fun next() {
            if (index.read() == listData.size - 1) index.save(0) else index.save(index.read() + 1)
        }

        override fun drawableRes() = listData[index.read()].first

        override fun descriptionRes() = listData[index.read()].second
    }
}