package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation

import com.github.andrewmaneshin.lemonadeapp.MyViewModel
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.LemonadeRepository

class LemonadeViewModel(
    private val repository: LemonadeRepository
) : MyViewModel {

    fun next(): LemonadeUiState {
        repository.next()
        return if (repository.isSqueeze())
            LemonadeUiState.Squeeze(repository.drawableRes(), repository.descriptionRes())
        else
            LemonadeUiState.Base(repository.drawableRes(), repository.descriptionRes())
    }

    fun init(isFirstRun: Boolean): LemonadeUiState {
        return if (isFirstRun) {
            LemonadeUiState.Base(repository.drawableRes(), repository.descriptionRes())
        } else {
            LemonadeUiState.Empty
        }
    }
}