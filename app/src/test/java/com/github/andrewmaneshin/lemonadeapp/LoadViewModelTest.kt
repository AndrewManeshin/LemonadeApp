package com.github.andrewmaneshin.lemonadeapp

import com.github.andrewmaneshin.lemonadeapp.load.data.LoadRepository
import com.github.andrewmaneshin.lemonadeapp.load.data.LoadResult
import com.github.andrewmaneshin.lemonadeapp.load.data.RunAsync
import com.github.andrewmaneshin.lemonadeapp.load.presentation.LoadUiState
import com.github.andrewmaneshin.lemonadeapp.load.presentation.LoadViewModel
import com.github.andrewmaneshin.lemonadeapp.load.presentation.UiObservable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoadViewModelTest {

    private lateinit var repository: FakeLoadRepository
    private lateinit var observable: FakeUiObservable
    private lateinit var runAsync: FakeRunAsync
    private lateinit var viewModel: LoadViewModel
    private lateinit var fragment: FakeFragment

    @Before
    fun setUp() {
        repository = FakeLoadRepository()
        observable = FakeUiObservable()
        runAsync = FakeRunAsync()
        viewModel = LoadViewModel(
            repository = repository,
            observable = observable,
            runAsync = runAsync
        )
        fragment = FakeFragment()
    }

    @Test
    fun sameFragment() {
        repository.expectResult(LoadResult.Success)

        viewModel.load(isFirstRun = true)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(1, observable.postUiStateCalledList.size)

        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = fragment)
        assertEquals(1, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        runAsync.returnResult()

        assertEquals(LoadUiState.Success, observable.postUiStateCalledList[1])
        assertEquals(2, observable.postUiStateCalledList.size)
        assertEquals(LoadUiState.Success, fragment.statesList[1])
        assertEquals(2, fragment.statesList.size)
    }

    @Test
    fun recreateActivity() {
        repository.expectResult(LoadResult.Error(message = "no internet connection"))

        viewModel.load(isFirstRun = true)
        assertEquals(LoadUiState.Progress, observable.postUiStateCalledList.first())
        assertEquals(1, observable.postUiStateCalledList.size)

        assertEquals(1, repository.loadCalledCount)

        viewModel.startUpdates(observer = fragment)
        assertEquals(1, observable.registerCalledCount)

        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        viewModel.stopUpdates()
        assertEquals(1, observable.unregisterCalledCount)

        runAsync.returnResult()
        assertEquals(1, fragment.statesList.size)
        assertEquals(
            LoadUiState.Error(message = "no internet connection"),
            observable.postUiStateCalledList[1]
        )
        assertEquals(2, observable.postUiStateCalledList.size)

        val newInstanceOfFragment = FakeFragment()

        viewModel.load(isFirstRun = false)
        assertEquals(1, repository.loadCalledCount)
        assertEquals(2, observable.postUiStateCalledList.size)

        viewModel.startUpdates(observer = newInstanceOfFragment)
        assertEquals(2, observable.registerCalledCount)

        assertEquals(
            LoadUiState.Error(message = "no internet connection"),
            newInstanceOfFragment.statesList.first()
        )
        assertEquals(1, newInstanceOfFragment.statesList.size)
    }

    private class FakeFragment : (LoadUiState) -> Unit {

        val statesList = mutableListOf<LoadUiState>()

        override fun invoke(p1: LoadUiState) {
            statesList.add(p1)
        }
    }

    private class FakeLoadRepository : LoadRepository {

        private var loadResult: LoadResult? = null

        fun expectResult(expected: LoadResult) {
            loadResult = expected
        }

        var loadCalledCount = 0

        override fun load(): LoadResult {
            loadCalledCount++
            return loadResult!!
        }
    }

    private class FakeUiObservable : UiObservable {

        private var uiStateCached: LoadUiState? = null
        private var observerCached: ((LoadUiState) -> Unit)? = null

        var registerCalledCount = 0

        override fun register(observer: (LoadUiState) -> Unit) {
            registerCalledCount++
            observerCached = observer
            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached!!)
                uiStateCached = null
            }
        }

        var unregisterCalledCount = 0
        override fun unregister() {
            unregisterCalledCount++
            observerCached = null
        }

        val postUiStateCalledList = mutableListOf<LoadUiState>()

        override fun postUiState(uiState: LoadUiState) {
            postUiStateCalledList.add(uiState)
            if (observerCached == null) {
                uiStateCached = uiState
            } else {
                observerCached!!.invoke(uiState)
                uiStateCached = null
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private class FakeRunAsync : RunAsync {

        private var result: Any? = null
        private var ui: (Any) -> Unit = {}

        override fun <T : Any> handleAsync(heavyOperation: () -> T, uiUpdate: (T) -> Unit) {
            result = heavyOperation.invoke()
            ui = uiUpdate as (Any) -> Unit
        }

        fun returnResult() {
            ui.invoke(result!!)
        }
    }
}