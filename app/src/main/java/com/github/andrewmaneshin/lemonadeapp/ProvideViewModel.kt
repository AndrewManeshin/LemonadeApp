package com.github.andrewmaneshin.lemonadeapp

import com.github.andrewmaneshin.lemonadeapp.lemonade.di.ProvideLemonadeViewModel
import com.github.andrewmaneshin.lemonadeapp.load.di.ProvideLoadViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T

    class Make(core: Core) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideLemonadeViewModel(core, chain)
            chain = ProvideLoadViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
            chain.makeViewModel(clazz)
    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
            throw IllegalStateException("unknown class $clazz")
        }
    }
}