package com.example.assignment.models.onedayresponse

import com.example.assignment.models.WeatherCityList
import com.google.gson.annotations.SerializedName

data class OneDayWeatherList(
    @SerializedName("list")
    var oneDayList: List<WeatherCityList>,
):java.io.Serializable
