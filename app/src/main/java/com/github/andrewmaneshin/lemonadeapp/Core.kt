package com.github.andrewmaneshin.lemonadeapp

import android.content.Context

class Core(context: Context, val clearViewModel: ClearViewModel) {

    val sharedPreferences = context.getSharedPreferences("LemonadeApp", Context.MODE_PRIVATE)

    val countCache = IntCache.Base(sharedPreferences, "count_number", 1)
}