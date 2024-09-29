package com.github.andrewmaneshin.lemonadeapp.load.presentation

import com.github.andrewmaneshin.lemonadeapp.MyViewModel
import com.github.andrewmaneshin.lemonadeapp.load.data.LoadRepository
import com.github.andrewmaneshin.lemonadeapp.load.data.RunAsync

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable,
    private val runAsync: RunAsync
) : MyViewModel {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            runAsync.handleAsync({
                val result = repository.load()
                if (result.isSuccessful())
                    LoadUiState.Success
                else
                    LoadUiState.Error(result.message())
            }) {
                observable.postUiState(it)
            }
        }
    }

    fun startUpdates(observer: (LoadUiState) -> Unit) {
        observable.register(observer)
    }

    fun stopUpdates() {
        observable.unregister()
    }
}