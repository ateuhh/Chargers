package com.example.charges.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charges.Status
import com.example.charges.domain.CitiesListInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CitiesListViewModel(private val citiesListInteractor: CitiesListInteractor): ViewModel() {

    private val _citiesListLiveData = MutableLiveData<List<String>>()
    val citiesListLiveData: LiveData<List<String>> = _citiesListLiveData

    private val _statusLiveData = MutableLiveData<Status>()
    val statusLiveData: LiveData<Status> = _statusLiveData

    init {
        viewModelScope.launch {
            _statusLiveData.postValue(Status.Loading)
            delay(1000L)
            try {
                val response = citiesListInteractor.getCities().toList()
                _statusLiveData.postValue(Status.Success)
                _citiesListLiveData.postValue(citiesListInteractor.getCities().toList())
            } catch (e: Exception) {
                _statusLiveData.postValue(Status.Error(e.message.orEmpty(), e))
            }
        }
    }
}