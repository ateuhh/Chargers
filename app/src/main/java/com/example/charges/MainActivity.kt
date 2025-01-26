package com.example.charges

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.charges.presentation.chargers.ChargersFragment.Companion.ARG_CITY

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun toCitySelect() {
        navController.popBackStack()
    }

    override fun toChargers(city: String) {
        navController.navigate(R.id.chargersFragment, bundleOf(ARG_CITY to city))
    }
}
