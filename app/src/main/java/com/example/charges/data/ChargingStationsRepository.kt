package com.example.charges.data

import android.content.Context
import com.example.charges.App.Companion.instance
import com.example.charges.data.models.ChargingStation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChargingStationsRepository {

    suspend fun getChargers(): List<ChargingStation> = withContext(Dispatchers.IO) {
        val jsonString =
            readJsonFromAssets(instance.applicationContext, "server_sample_response.json")
        parseJsonToModel(jsonString)

    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseJsonToModel(jsonString: String): List<ChargingStation> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<ChargingStation>>() {}.type)
    }
}