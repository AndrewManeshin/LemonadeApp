package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.andrewmaneshin.lemonadeapp.App
import com.github.andrewmaneshin.lemonadeapp.databinding.FragmentLemonadeBinding

class LemonadeFragment : Fragment() {

    private var _binding: FragmentLemonadeBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLemonadeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity().application as App).viewModel
        lateinit var uiState: LemonadeUiState
        val update = {
            with(binding) {
                uiState.update(
                    imageButton,
                    descriptionTextView
                )
            }
        }

        binding.imageButton.setOnClickListener {
            uiState = viewModel.next()
            update.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        update.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}