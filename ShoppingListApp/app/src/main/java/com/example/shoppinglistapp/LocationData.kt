package com.example.shoppinglistapp

data class LocationData(
    val latitude: Double,
    val longitude: Double
)

data class GeocodeingResponse(
    val results: List<GeocodingResults>
)

data class GeocodingResults(
    val formatted_address: String
)