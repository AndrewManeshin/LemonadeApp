package com.github.andrewmaneshin.lemonadeapp

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.github.andrewmaneshin.lemonadeapp.lemonade.LemonadePage
import com.github.andrewmaneshin.lemonadeapp.load.LoadPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var lemonadePage: LemonadePage
    private lateinit var loadPage: LoadPage

    @Before
    fun setUp() {
        lemonadePage = LemonadePage()
        loadPage = LoadPage()
        InstrumentationRegistry.getInstrumentation().targetContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        ).edit().clear().apply()
    }

    @Test
    fun successTest() {
        checkWithRecreate { lemonadePage.assertTreeState() }
        lemonadePage.clickImage()
        lemonadePage.doesNotExist()

        checkWithRecreate { loadPage.assertProgressState() }
        loadPage.waitTillGone()

        checkWithRecreate { lemonadePage.assertSqueezeState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertDrinkState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertRestartState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertTreeState() }
    }

    @Test
    fun errorTest() {
        checkWithRecreate { lemonadePage.assertTreeState() }
        lemonadePage.clickImage()
        lemonadePage.doesNotExist()

        checkWithRecreate { loadPage.assertProgressState() }
        loadPage.waitTillError()

        checkWithRecreate { loadPage.assertErrorState() }
        loadPage.clickRetry()

        checkWithRecreate { loadPage.assertProgressState() }
        loadPage.waitTillGone()

        checkWithRecreate { lemonadePage.assertSqueezeState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertDrinkState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertRestartState() }
        lemonadePage.clickImage()
        checkWithRecreate { lemonadePage.assertTreeState() }
    }

    private fun checkWithRecreate(block: () -> Unit) {
        block.invoke()
        scenarioRule.scenario.recreate()
        block.invoke()
    }
}