package com.example.androidassessmenttask.ui.history.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.androidassessmenttask.base.AndroidAssessmentBaseViewModel
import com.example.androidassessmenttask.core.models.UserDataModel
import com.example.androidassessmenttask.core.network.AndroidAssessmentBaseRepository
import com.example.androidassessmenttask.core.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: AndroidAssessmentBaseRepository
) : AndroidAssessmentBaseViewModel() {

    private val _historyStateFlow = MutableStateFlow<Resource<List<UserDataModel>>?>(null)
    val historyStateFlow = _historyStateFlow.asStateFlow()

    fun fetchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            _historyStateFlow.emit(Resource.Loading())
            when (val apiResponse = repository.fetchHistory()) {
                is Resource.Loading -> {}
                is Resource.Failure -> {
                    _historyStateFlow.emit(apiResponse)
                }

                is Resource.Success -> {
                    _historyStateFlow.emit(apiResponse)
                }
            }
        }
    }
}