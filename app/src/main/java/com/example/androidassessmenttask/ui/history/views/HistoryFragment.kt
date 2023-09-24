package com.example.androidassessmenttask.ui.history.views

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidassessmenttask.R
import com.example.androidassessmenttask.base.AndroidAssessmentBaseFragment
import com.example.androidassessmenttask.core.network.Resource
import com.example.androidassessmenttask.databinding.FragmentHistoryBinding
import com.example.androidassessmenttask.ui.history.viewmodels.HistoryViewModel
import com.example.androidassessmenttask.ui.history.views.adapters.HistoryFragmentAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : AndroidAssessmentBaseFragment<FragmentHistoryBinding>() {
    private val viewModel: HistoryViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_history

    override fun setupViews() {
        viewModel.fetchHistory()
    }

    override fun setListeners() {
    }

    override fun setObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.historyStateFlow.collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> {
                            binding.noRecordsText.text =
                                resources.getString(R.string.fetching_history)
                            binding.noRecordsText.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is Resource.Failure -> {
                            binding.noRecordsText.text =
                                resources.getString(R.string.whoops_having_trouble)
                            binding.noRecordsText.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            if (result.data.isEmpty()) {
                                binding.noRecordsText.text =
                                    resources.getString(R.string.no_records_to_show)
                                binding.noRecordsText.visibility = View.VISIBLE
                            } else {
                                binding.noRecordsText.visibility = View.GONE
                                binding.recyclerView.visibility = View.VISIBLE
                                binding.recyclerView.adapter = HistoryFragmentAdapter(result.data)
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }

}