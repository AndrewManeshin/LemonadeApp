package com.github.andrewmaneshin.lemonadeapp

import com.github.andrewmaneshin.lemonadeapp.lemonade.data.LemonadeRepository
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeUiState
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LemonadeViewModelTest {

    private lateinit var viewModel: LemonadeViewModel
    private lateinit var fakeRepository: FakeLemonadeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeLemonadeRepository()
        viewModel = LemonadeViewModel(fakeRepository)
    }

    @Test
    fun test1() {
        var actual: LemonadeUiState = viewModel.init(true)
        var expected: LemonadeUiState = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_tree,
            descriptionResId = R.string.tree_description
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_squeeze,
            descriptionResId = R.string.squeeze_description
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_drink,
            descriptionResId = R.string.drink_description
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_restart,
            descriptionResId = R.string.restart_description
        )
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_tree,
            descriptionResId = R.string.tree_description
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test2() {
        var actual: LemonadeUiState = viewModel.init(isFirstRun = true)
        var expected: LemonadeUiState = LemonadeUiState.Base(
            imageResId = R.drawable.lemon_tree,
            descriptionResId = R.string.tree_description
        )
        assertEquals(expected, actual)

        actual = viewModel.init(isFirstRun = false)
        expected = LemonadeUiState.Empty
        assertEquals(expected, actual)
    }
}

private class FakeLemonadeRepository : LemonadeRepository {

    private val listData = listOf(
        Pair(R.drawable.lemon_tree, R.string.tree_description),
        Pair(R.drawable.lemon_squeeze, R.string.squeeze_description),
        Pair(R.drawable.lemon_drink, R.string.drink_description),
        Pair(R.drawable.lemon_restart, R.string.restart_description)
    )
    private var index = 0

    override fun next() {
        if (index == listData.size - 1) index = 0 else ++index
    }

    override fun drawableRes() = listData[index].first

    override fun descriptionRes() = listData[index].second
}