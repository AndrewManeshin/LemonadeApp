package com.github.andrewmaneshin.lemonadeapp

class LemonadeViewModel(
    private val repository: LemonadeRepository
) {

    fun next(): LemonadeUiState {
        repository.next()
        return LemonadeUiState.Base(repository.drawableRes(), repository.descriptionRes())
    }

    fun init(isFirstRun: Boolean): LemonadeUiState {
        return if (isFirstRun) {
            LemonadeUiState.Base(repository.drawableRes(), repository.descriptionRes())
        } else {
            LemonadeUiState.Empty
        }
    }
}