package com.example.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.networkcalls.Response
import com.example.assignment.repository.WeatherRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    private val weatherData = MutableLiveData<Response<OneDayWeatherList>>()
    val weatherSharedData : LiveData<Response<OneDayWeatherList>> get() = weatherData
    private val repository = WeatherRepository()

    fun fetchData(city: String,apiKey:String){
        GlobalScope.launch{
            try{
                val data = repository.getWeatherData(city,apiKey)
                weatherData.postValue(Response.Success(data))
            } catch(e:Exception){
                weatherData.postValue(Response.Error(e.message.toString()))
            }
        }
    }
}