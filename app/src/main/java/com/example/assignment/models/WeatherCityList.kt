package com.example.assignment.models

data class WeatherCityList(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val cnt:Int,
    //val timezone: Int,
    val dt_txt:String,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
    var city: String?
):java.io.Serializable
