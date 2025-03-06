package com.example.shoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel:ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResults>())
    val address: State<List<GeocodingResults>> = _address

    fun updateState(newLocationData: LocationData) {
        _location.value = newLocationData
    }

    //get the address
    fun fetchAddress(latlng: String) {
        try {
            viewModelScope.launch {
                val result  = RetrofitClient.create().getAddressFromCoordinate(latlng, "AIzaSyCB_BQdLMtQ_4Xx9k8-nF6PwodXOu3vLJg")
                _address.value = result.results
            }
        } catch (e: Exception) {
            Log.d("res1", "${e.cause} - ${e.message}")
        }
    }
}