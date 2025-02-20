package com.example.recipeappstudy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    //viewmodel 상속
    private val _categoriesState = mutableStateOf(RecipeState())
    //외부로 노출될 변수
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCateogries()
    }

    //fecth data from server
    private fun fetchCateogries() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(isLoading = true, errorMessage = null, list = response)
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(isLoading = false, errorMessage = "fail to load data")
            }

        }
    }


    //관리할 상태
    data class RecipeState(
        val isLoading: Boolean = true,
        val list: List<Category> = emptyList(),
        val errorMessage: String? = null
    )
}