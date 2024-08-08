package com.example.assignment.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.repository.WeatherRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    private val weatherData = MutableLiveData<OneDayWeatherList>()
    val weatherSharedData : LiveData<OneDayWeatherList> get() = weatherData
    private val repository = WeatherRepository()
    var check: Int? = null
    fun fetchData(city: String,apiKey:String){
        GlobalScope.launch{
            val data = repository.getWeatherData(city,apiKey)
            check = repository.check
            Log.d("WEATHERVIEWMODEL","VM ${check}")
            //Log.d("WEATHERVIEWMODEL",data.toString())
            weatherData.postValue(data)
        }
    }
}