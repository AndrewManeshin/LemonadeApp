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

    @Before
    fun setUp() {
        lemonadePage = LemonadePage(InstrumentationRegistry.getInstrumentation().targetContext)
        InstrumentationRegistry.getInstrumentation().targetContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        ).edit().clear().apply()
    }

    @Test
    fun test1() {
        lemonadePage.assertTreeState()
        lemonadePage.clickImage()
        lemonadePage.assertSqueezeState()
        lemonadePage.clickImage()
        lemonadePage.assertDrinkState()
        lemonadePage.clickImage()
        lemonadePage.assertRestartState()
        lemonadePage.clickImage()
        lemonadePage.assertTreeState()
    }

    @Test
    fun test2() {
        lemonadePage.assertTreeState()
        lemonadePage.clickImage()
        lemonadePage.assertSqueezeState()

        scenarioRule.scenario.recreate()
        lemonadePage.assertSqueezeState()
    }
}