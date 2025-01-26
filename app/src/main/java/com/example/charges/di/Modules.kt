package com.example.charges.di

import com.example.charges.domain.ChargersListInteractor
import com.example.charges.presentation.chargers.ChargersListViewModel
import com.example.charges.data.ChargingStationsRepository
import com.example.charges.domain.CitiesListInteractor
import com.example.charges.presentation.cities.CitiesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { (city: String) -> ChargersListViewModel(city, get()) }
    viewModel { CitiesListViewModel(get()) }

    single { ChargersListInteractor(get()) }
    single { CitiesListInteractor(get()) }

    single { ChargingStationsRepository() }
}