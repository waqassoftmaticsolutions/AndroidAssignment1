package com.example.assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Adaptor
import com.example.assignment.R
import com.example.assignment.models.WeatherCityList
import com.example.assignment.networkcalls.RetrofitObject
import com.example.assignment.networkcalls.RetrofitObject.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var weatherAdapter: Adaptor
//    private val weatherListData: MutableList<WeatherCityList> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val TAG = "WEATHER_API"
//        val city = "London"
//        val apiKey = "aea518baa59e62768dc526804734c47d"
//        val api = RetrofitObject.api
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Initialize the adapter with an empty list
//        weatherAdapter = Adaptor(weatherListData, this)
//        recyclerView.adapter = weatherAdapter
//
//        // Fetch weather data
//        fetchWeatherData(city, apiKey)
//    }
//
//    private fun fetchWeatherData(city: String, apiKey: String) {
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                Log.d("WEATHER_API", "Fetching weather data...")
//                val response = api.getWeather(city, apiKey)
//                withContext(Dispatchers.Main) {
//                    // Update the list and notify the adapter
//                    weatherListData.clear()
//                    weatherListData.addAll(response.oneDayList)
//                    weatherAdapter.notifyDataSetChanged()
//                }
//                Log.d("WEATHER_API", "Weather data fetched successfully: ${response}")
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("WEATHER_API", "Error fetching weather data: ${e.message}")
//            }
//        }
//    }
//}




class MainActivity : AppCompatActivity() {
    //private lateinit var weatherAdapter: Adaptor
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city = findViewById<EditText>(R.id.input)
        val button = findViewById<Button>(R.id.clickButton)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, WeatherActivity::class.java)
            val data = city.text.toString()
            intent.putExtra("city",data)
            startActivity(intent)
        }
        //val city = "London"
        //val apiKey = "aea518baa59e62768dc526804734c47d"
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //weatherAdapter = Adaptor(weatherListData, this)
        //recyclerView.adapter = weatherAdapter
        //fetchWeatherData(city, apiKey)
    }


}