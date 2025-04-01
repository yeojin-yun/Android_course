package com.example.wishlistapp

import android.app.Application

class WishApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}