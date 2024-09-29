package com.github.andrewmaneshin.lemonadeapp.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.andrewmaneshin.lemonadeapp.App
import com.github.andrewmaneshin.lemonadeapp.databinding.FragmentLoadBinding
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.NavigateToLemonade

class LoadFragment : Fragment() {

    private val viewModel: LoadViewModel by lazy {
        (requireActivity().application as App).loadViewModel
    }
    private var _binding: FragmentLoadBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewModel = (requireActivity().application as App).loadViewModel

        binding.retryButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.load(isFirstRun = savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdates(observer = { uiState ->
            uiState.show(
                binding.progressBar,
                binding.retryButton,
                binding.errorTextView
            )
            uiState.navigate(requireActivity() as NavigateToLemonade)
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}