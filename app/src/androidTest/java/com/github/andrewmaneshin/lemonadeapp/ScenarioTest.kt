package com.github.andrewmaneshin.lemonadeapp

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var lemonadePage: LemonadePage
    private fun checkWithRecreate(block: () -> Unit) {
        block.invoke()
        scenarioRule.scenario.recreate()
        block.invoke()
    }

    @Before
    fun setUp() {
        lemonadePage = LemonadePage()
        InstrumentationRegistry.getInstrumentation().targetContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        ).edit().clear().apply()
    }

    @Test
    fun test1() {
        checkWithRecreate { lemonadePage.assertTreeState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertSqueezeState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertDrinkState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertRestartState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertTreeState() }
    }
}