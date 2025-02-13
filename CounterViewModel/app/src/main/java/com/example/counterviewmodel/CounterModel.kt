package com.example.counterviewmodel

data class CounterModel(var count: Int)

class CounterRepository {
    private var _counter: CounterModel = CounterModel(0)
    fun getCount() = _counter

    fun increment() {
        _counter.count++
    }

    fun decrement() {
        _counter.count--
    }
}
