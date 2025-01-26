package com.example.charges.presentation.chargers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.charges.MainActivity
import com.example.charges.R
import com.example.charges.databinding.FragmentChargersBinding
import dev.androidbroadcast.vbpd.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChargersFragment : Fragment(R.layout.fragment_chargers) {

    private val viewModel: ChargersListViewModel by viewModel {
        parametersOf(
            arguments?.getString(ARG_CITY).orEmpty()
        )
    }
    private val binding: FragmentChargersBinding by viewBinding(FragmentChargersBinding::bind)
    private val chargersAdapter by lazy { ChargersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.chargesLiveData.observe(viewLifecycleOwner) { data ->
            chargersAdapter.items = data
            binding.chargersList.apply {
                adapter = chargersAdapter
            }
        }
    }

     private fun initListeners() {
         binding.back.setOnClickListener {
             (requireActivity() as? MainActivity)?.toCitySelect()
         }
     }

    companion object {
        const val ARG_CITY = "city"
    }
}