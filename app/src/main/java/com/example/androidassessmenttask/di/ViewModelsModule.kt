package com.example.androidassessmenttask.di

import com.example.androidassessmenttask.ui.history.viewmodels.HistoryViewModel
import com.example.androidassessmenttask.ui.home.viewmodels.HomeViewModel
import com.example.androidassessmenttask.ui.home.viewmodels.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel { HomeViewModel() }
    viewModel { ResultViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}