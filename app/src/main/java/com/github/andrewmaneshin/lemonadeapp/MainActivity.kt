package com.github.andrewmaneshin.lemonadeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeFragment
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeScreen
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.NavigateToLemonade
import com.github.andrewmaneshin.lemonadeapp.load.presentation.LoadScreen
import com.github.andrewmaneshin.lemonadeapp.load.presentation.NavigateToLoad

class MainActivity : AppCompatActivity(), Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LemonadeFragment())
            .commit()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)
}

interface Navigate : NavigateToLemonade, NavigateToLoad {

    fun navigate(screen: Screen)

    override fun navigateToLemonade() = navigate(LemonadeScreen)

    override fun navigateToLoad() = navigate(LoadScreen)
}

interface Screen {

    fun show(containerId: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment: Class<out Fragment>) : Screen {

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, newFragment())
                .commit()
        }

        protected open fun newFragment(): Fragment = fragment.getDeclaredConstructor().newInstance()
    }
}