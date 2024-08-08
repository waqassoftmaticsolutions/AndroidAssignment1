package com.example.assignment.networkcalls

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    //val apiKey = "aea518baa59e62768dc526804734c47d"
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val api: MyAPI by lazy{
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
    }
}