package com.example.androidassessmenttask.ui.home.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.androidassessmenttask.base.AndroidAssessmentBaseViewModel
import com.example.androidassessmenttask.core.models.UserDataModel
import com.example.androidassessmenttask.core.network.AndroidAssessmentBaseRepository
import com.example.androidassessmenttask.core.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResultViewModel(
    private val repository: AndroidAssessmentBaseRepository
) : AndroidAssessmentBaseViewModel() {
    private val _nameStateFlow = MutableStateFlow<Resource<UserDataModel>?>(null)
    val nameStateFlow = _nameStateFlow.asStateFlow()

    fun fetchAge(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _nameStateFlow.emit(Resource.Loading())
            when (val apiResponse = repository.fetchAge(name)) {
                is Resource.Loading -> {}
                is Resource.Failure -> {
                    _nameStateFlow.emit(apiResponse)
                }

                is Resource.Success -> {
                    _nameStateFlow.emit(apiResponse)
                }
            }
        }
    }
}