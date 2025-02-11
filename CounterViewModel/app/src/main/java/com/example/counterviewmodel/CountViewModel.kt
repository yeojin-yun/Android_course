package com.example.counterviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel(private  val repository: CounterRepository): ViewModel() {
    private val _count = mutableStateOf(repository.getCount().count)
    val count: MutableState<Int> = _count

    fun increment() {
        repository.increment()
        _count.value = repository.getCount().count
    }

    fun decrement() {
        repository.decrement()
        _count.value = repository.getCount().count
    }
}