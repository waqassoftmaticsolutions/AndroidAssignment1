package com.example.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.WeatherActivity
import com.example.assignment.models.WeatherCityList
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.repository.WeatherRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {
    private val weatherData = MutableLiveData<WeatherCityList>()
    val weatherSharedData : LiveData<WeatherCityList> get() = weatherData
    fun setDetails(detail: WeatherCityList){
        weatherData.value = detail
    }
}