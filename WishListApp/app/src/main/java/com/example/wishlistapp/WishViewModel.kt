package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel: ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    //title change
    fun wishTitleChanged(newValue: String) {
        wishTitleState = newValue
    }

    //description change
    fun wishDescriptionChanged(newValue: String) {
        wishDescriptionState = newValue
    }
}