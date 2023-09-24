package com.example.androidassessmenttask.ui.home.views

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidassessmenttask.R
import com.example.androidassessmenttask.base.AndroidAssessmentBaseFragment
import com.example.androidassessmenttask.core.network.Resource
import com.example.androidassessmenttask.databinding.FragmentResultBinding
import com.example.androidassessmenttask.ui.home.viewmodels.ResultViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : AndroidAssessmentBaseFragment<FragmentResultBinding>() {
    private val viewModel: ResultViewModel by viewModel()
    private val navArgs: ResultFragmentArgs by navArgs()

    override fun getLayout() = R.layout.fragment_result

    override fun setupViews() {
        viewModel.fetchAge(navArgs.userName)
    }

    override fun setListeners() {
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.nameStateFlow.collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> {
                            binding.resultText = resources.getString(
                                R.string.guessing_your_age,
                            )
                        }

                        is Resource.Failure -> {
                            Toast.makeText(activity, result.throwable.message, Toast.LENGTH_SHORT)
                                .show()
                            binding.resultText = resources.getString(
                                R.string.whoops_having_trouble,
                            )
                        }

                        is Resource.Success -> {
                            Toast.makeText(activity, result.data.age.toString(), Toast.LENGTH_SHORT)
                                .show()
                            binding.resultText = resources.getString(
                                R.string.your_age_is,
                                result.data.name,
                                result.data.age
                            )
                        }

                        else -> {}
                    }
                }
            }
        }
    }

}