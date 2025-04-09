package com.example.musicapp

import androidx.annotation.DrawableRes

data class LibraryData(
    val title: String,
    @DrawableRes val icon: Int
)

val libraries = listOf<LibraryData>(
    LibraryData("PlayList", R.drawable.baseline_grass_24),
    LibraryData("Artist", R.drawable.baseline_heart_broken_24),
    LibraryData("Album", R.drawable.baseline_kebab_dining_24),
    LibraryData("Songs", R.drawable.baseline_mood_24),
    LibraryData("Genre", R.drawable.baseline_outlet_24),
)