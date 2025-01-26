package com.example.charges.presentation.cities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.charges.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.charges.R
import com.example.charges.Status
import com.example.charges.databinding.FragmentCitiesBinding
import dev.androidbroadcast.vbpd.viewBinding

class CitiesFragment : Fragment(R.layout.fragment_cities) {
    private val citiesAdapter by lazy { CitiesAdapter() }
    private val viewModel: CitiesListViewModel by viewModel()
    private val binding: FragmentCitiesBinding by viewBinding(FragmentCitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        with(viewModel) {
            citiesListLiveData.observe(viewLifecycleOwner) { data ->
                citiesAdapter.setData(data)
                binding.cities.adapter = citiesAdapter
            }
            statusLiveData.observe(viewLifecycleOwner) {
                binding.loadingView.isVisible = it == Status.Loading
            }
        }
    }

    private fun initListeners() {
        binding.navigateToDetails.setOnClickListener {
            val city = citiesAdapter.getSelectedItem()
            city?.let {
                (requireActivity() as? MainActivity)?.toChargers(it)
            } ?: Toast.makeText(context, getString(R.string.empty_city_error), Toast.LENGTH_LONG)
                .show()
        }
    }
}
