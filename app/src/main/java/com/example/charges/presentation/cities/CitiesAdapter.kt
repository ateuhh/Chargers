package com.example.charges.presentation.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charges.databinding.ItemCityBinding

class CitiesAdapter : RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    private val data = mutableListOf<String>()

    private var previousSelectedPosition = -1
    private var currentSelectedPosition = -1

    fun setData(cities: List<String>) {
        data.clear()
        data.addAll(cities)
    }

    fun getSelectedItem(): String? {
        return if (currentSelectedPosition == -1) {
            null
        } else {
            data[currentSelectedPosition]
        }
    }

    fun updateSelectedPosition(position: Int) {
        previousSelectedPosition = currentSelectedPosition
        if (previousSelectedPosition > -1) {
            notifyItemChanged(previousSelectedPosition)
        }
        currentSelectedPosition = position
        notifyItemChanged(currentSelectedPosition)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CitiesAdapter.CitiesViewHolder {
        return CitiesViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CitiesAdapter.CitiesViewHolder, position: Int) {
        holder.itemView.setOnClickListener { updateSelectedPosition(position) }
        holder.bind(data[position], position == currentSelectedPosition, position)
    }

    override fun getItemCount() = data.size

    inner class CitiesViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: String, selected: Boolean, position: Int) {
            with(binding) {
                cityName.text = city
                selectButton.isChecked = selected
                selectButton.setOnClickListener {
                    updateSelectedPosition(position)
                }
            }
        }
    }


}
