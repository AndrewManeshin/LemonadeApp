package com.github.andrewmaneshin.lemonadeapp.load.data

interface LoadResult {

    fun message(): String

    fun isSuccessful(): Boolean

    object Success : LoadResult {
        override fun message(): String = throw IllegalStateException("cannot happen")

        override fun isSuccessful(): Boolean = true
    }

    data class Error(private val message: String) : LoadResult {
        override fun message(): String = message

        override fun isSuccessful(): Boolean = false
    }
}