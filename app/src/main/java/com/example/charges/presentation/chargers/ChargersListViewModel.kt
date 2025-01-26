package com.example.charges.presentation.chargers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charges.domain.ChargersListInteractor
import com.example.charges.data.models.Charger
import kotlinx.coroutines.launch

class ChargersListViewModel(
    private val city: String,
    private val chargersListInteractor: ChargersListInteractor
) : ViewModel() {

    private val _chargersMutableData = MutableLiveData<List<Charger>>()
    val chargesLiveData: LiveData<List<Charger>> = _chargersMutableData

    init {
        viewModelScope.launch {
            val response = chargersListInteractor.getChargersSortedList(city)
            _chargersMutableData.postValue(response)
        }
    }
}