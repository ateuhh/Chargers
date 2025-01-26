package com.example.charges.domain

import com.example.charges.data.ChargingStationsRepository
import com.example.charges.data.models.Charger

class ChargersListInteractor(private val repository: ChargingStationsRepository) {

    suspend fun getChargersSortedList(city: String): List<Charger> {
        val cityChargers = repository.getChargers().filter { it.city == city }
        return cityChargers.map { it.charger }.sortedBy { it.name }
    }

}