package com.example.recipeappstudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://staging-api.buyfuture.io/biz_app/api/v1/").addConverterFactory(GsonConverterFactory.create()).build()
val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("booking-category/")
    suspend fun getCategories(): List<Category>
}