package com.acronymsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.acronymsapp.repository.Repository
import com.acronymsapp.repository.remote.model.Result

class AcromineViewModel(val repository: Repository) : ViewModel() {

    fun fetchMeanings(shortForm: String): LiveData<Result> = liveData {
        val data = repository.fetchMeanings(shortForm)
        emit(data)
    }
}

class AcromineViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AcromineViewModel::class.java)) {
            return AcromineViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
