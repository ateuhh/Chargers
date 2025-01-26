package com.example.charges.domain

import com.example.charges.data.ChargingStationsRepository

class CitiesListInteractor(private val repository: ChargingStationsRepository) {

    suspend fun getCities(): Set<String> {
        return repository.getChargers().map { it.city }.toSet()
    }
}