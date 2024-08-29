package com.github.andrewmaneshin.lemonadeapp

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.andrewmaneshin.lemonadeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = LemonadeViewModel(
            LemonadeRepository.Base(
                IntCache.Base(
                    applicationContext.getSharedPreferences(
                        R.string.app_name.toString(),
                        Context.MODE_PRIVATE
                    ), "Index", 0
                )
            )
        )

        enableEdgeToEdge()
        setContentView(binding.rootLayout)
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imageButton.setOnClickListener {
            viewModel.next().update(
                binding.imageButton,
                binding.descriptionTextView
            )
        }

        viewModel.init(savedInstanceState == null).update(
            binding.imageButton,
            binding.descriptionTextView
        )
    }
}