package com.example.charges.presentation.chargers

import androidx.recyclerview.widget.DiffUtil
import com.example.charges.data.models.Charger

class ChargersDiffCallback : DiffUtil.ItemCallback<Charger>() {
    override fun areItemsTheSame(oldItem: Charger, newItem: Charger): Boolean {
        return oldItem.name == newItem.name && newItem.address == oldItem.address
    }

    override fun areContentsTheSame(oldItem: Charger, newItem: Charger): Boolean {
        return oldItem == newItem
    }
}