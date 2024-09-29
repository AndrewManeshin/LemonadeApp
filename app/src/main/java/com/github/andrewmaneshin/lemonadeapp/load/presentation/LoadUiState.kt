package com.github.andrewmaneshin.lemonadeapp.load.presentation

import com.github.andrewmaneshin.lemonadeapp.R
import com.github.andrewmaneshin.lemonadeapp.load.presentation.view.error.ErrorTextView
import com.github.andrewmaneshin.lemonadeapp.load.presentation.view.error.ErrorUiState
import com.github.andrewmaneshin.lemonadeapp.load.presentation.view.progress.LoadView
import com.github.andrewmaneshin.lemonadeapp.load.presentation.view.retry.RetryButton
import com.github.andrewmaneshin.lemonadeapp.load.presentation.view.visibility.VisibilityUiState

interface LoadUiState {

    fun show(progressBar: LoadView, retryButton: RetryButton, errorTextView: ErrorTextView)

    fun navigate(navigateToLemonade: NavigateToLemonade) = Unit

    abstract class Abstract(
        private val progressUiState: VisibilityUiState,
        private val retryUiState: VisibilityUiState,
        private val errorUiState: ErrorUiState
    ) : LoadUiState {
        override fun show(
            progressBar: LoadView,
            retryButton: RetryButton,
            errorTextView: ErrorTextView
        ) {
            progressBar.update(progressUiState)
            retryButton.update(retryUiState)
            errorTextView.update(errorUiState)
        }
    }

    object Progress : Abstract(VisibilityUiState.Visible, VisibilityUiState.Gone, ErrorUiState.Hide)

    object Success : Abstract(VisibilityUiState.Gone, VisibilityUiState.Gone, ErrorUiState.Hide) {

        override fun navigate(navigateToLemonade: NavigateToLemonade) =
            navigateToLemonade.navigateToLemonade()
    }

    data class Error(private val message: String) : //todo handle message String and resId same time
        Abstract(
            VisibilityUiState.Gone,
            VisibilityUiState.Visible,
            ErrorUiState.Show(R.string.no_internet_connection)
        )
}