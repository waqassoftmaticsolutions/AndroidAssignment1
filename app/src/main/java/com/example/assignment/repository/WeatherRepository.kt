package com.example.assignment.repository

import android.util.Log
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.networkcalls.RetrofitObject.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {
    var check: Int? = null
    suspend fun getWeatherData(city:String, key:String):OneDayWeatherList?{
        Log.d("WEATHER", "Fetching weather data...")
        val response = api.getWeather(city, key)
//        check=1
        //return response
        return try {
            //Log.d("WEATHER", "Weather data fetched successfully: ${response}")
            response
        } catch (e: Exception) {
            e.printStackTrace()
            check = 0
            //Log.e("WEATHER", "Error fetching weather data: ${e.message}")
            null
        }
    }
}