package com.wonhaeseong.mvc_mvp_mvvm_study.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository

class MainViewModelFactory(private val model: Repository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(model) as T
    }
}