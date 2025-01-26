package com.example.charges.presentation.chargers

import com.example.charges.R
import com.example.charges.databinding.ItemChargerBinding
import com.example.charges.data.models.Charger
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ChargersAdapter : AsyncListDifferDelegationAdapter<Charger>(
    ChargersDiffCallback(), chargerDelegate()
)

fun chargerDelegate() = adapterDelegateViewBinding<Charger, Charger, ItemChargerBinding>(
    { layoutInflater, root ->
        ItemChargerBinding.inflate(layoutInflater, root, false)
    },
) {
    bind {
        with(binding) {
            name.text = item.name
            address.text = item.address
            val backgroundColor = if (item.busy) {
                getColor(R.color.red)
            } else {
                getColor(R.color.green)
            }
            status.setBackgroundColor(backgroundColor)
        }
    }
}