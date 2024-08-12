package com.example.assignment.repository

import android.util.Log
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.networkcalls.RetrofitObject.api

class WeatherRepository {
    suspend fun getWeatherData(city:String, key:String): OneDayWeatherList{
        Log.d("WEATHER", "Fetching weather data...")
        return try{
            val result = api.getWeather(city,key)
            result
        } catch(e: Exception){
            throw e
        }
    }
}