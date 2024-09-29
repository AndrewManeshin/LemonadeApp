package com.github.andrewmaneshin.lemonadeapp

interface ManageViewModels : ProvideViewModel, ClearViewModel {

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val viewModelsMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            viewModelsMap[viewModelClass] = null
        }

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
            if (viewModelsMap[clazz] == null) {
                val viewModel = make.makeViewModel(clazz)
                viewModelsMap[clazz] = viewModel
                viewModel
            } else
                viewModelsMap[clazz] as T
    }
}