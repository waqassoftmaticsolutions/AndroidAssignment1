package com.example.assignment.networkcalls

import com.example.assignment.models.onedayresponse.OneDayWeatherList
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAPI {
    @GET("forecast")
    suspend fun getWeather(@Query("q") city:String?, @Query("appid") apiKey:String, @Query("units") units:String = "metric"): OneDayWeatherList
}