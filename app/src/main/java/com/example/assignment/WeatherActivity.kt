//package com.example.assignment
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.assignment.models.WeatherCityList
//import com.example.assignment.viewmodels.WeatherViewModel
//import kotlinx.coroutines.DelicateCoroutinesApi
//
//class WeatherActivity : AppCompatActivity() {
//    private lateinit var weatherAdapter: Adaptor
//    private val weatherListData: MutableList<WeatherCityList> = mutableListOf()
//    private lateinit var weatherViewModel: WeatherViewModel
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_weather)
////        var actionBar = getSupportActionBar()
////        if (actionBar != null) {
////
////            // Customize the back button
////            actionBar.setHomeAsUpIndicator(R.drawable.mybutton);
////
////            // showing the back button in action bar
////            actionBar.setDisplayHomeAsUpEnabled(true);
////        }
//
//        // showing the back button in action bar
//        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//
//        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
//        val city = intent.getStringExtra("city")
//        Log.d("MAIN", city.toString())
//        val apiKey = "aea518baa59e62768dc526804734c47d"
//
//          val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
////        recyclerView.layoutManager = LinearLayoutManager(this)
////        weatherAdapter = Adaptor(weatherListData) { weatherData ->
////            val intent = Intent(this, WeatherDetail::class.java)
////            intent.putExtra("WEATHER_DETAILS", weatherData)
////            startActivity(intent)
////        }
////        recyclerView.adapter = weatherAdapter
//
//        Log.d("WEATHER","First")
//        city?.let{
//            weatherViewModel.fetchData(city,apiKey)
//        }
//        Log.d("WEATHER","@nd")
//        Log.d("WEATHER",weatherViewModel.weatherSharedData.toString())
//        weatherViewModel.weatherSharedData.observe(this) { weatherList ->
//            weatherList?.let {
//                weatherAdapter = Adaptor(weatherList)
//                recyclerView.adapter = weatherAdapter
//            }
//        }
//        Log.d("WEATHER","First ${weatherViewModel}")
//        //fetchWeatherData(city, apiKey)
//
//        // Set the OnClickListener
//        weatherAdapter.setOnClickListener(object : Adaptor.OnClickListener {
//            override fun onClick(position: Int, model: WeatherCityList) {
//                val intent = Intent(this@WeatherActivity, WeatherDetail::class.java)
//                intent.putExtra(DETAILS, model)
//                startActivity(intent)
//            }
//        })
//    }
////    override fun onContextItemSelected(item: MenuItem): Boolean {
////        when (item.itemId) {
////            android.R.id.home -> {
////                finish()
////                return true
////            }
////        }
////        return super.onContextItemSelected(item)
////    }
//
////    @OptIn(DelicateCoroutinesApi::class)
////    private fun fetchWeatherData(city: String?, apiKey: String) {
////
////    }
//
//    companion object {
//        val DETAILS = "details"
//    }
//
//}


package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.models.WeatherCityList
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.viewmodels.WeatherViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherActivity : AppCompatActivity() {
    private lateinit var weatherAdapter: Adaptor
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        val city = intent.getStringExtra("city")
        //Log.d("MAIN", city.toString())
        val apiKey = "aea518baa59e62768dc526804734c47d"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        city?.let {
            weatherViewModel.fetchData(it, apiKey)
        }
        Log.d("MAIN", weatherViewModel.check.toString())
        if(weatherViewModel.check == 0){
            val intent = Intent(this@WeatherActivity, MainActivity::class.java)
            //intent.putExtra("Error","Error in Fetching data Enter correct city name")
            startActivity(intent)
        }
        weatherViewModel.weatherSharedData.observe(this) { weatherList ->
            weatherList?.let {
                weatherAdapter = Adaptor(it, this).apply {
                    // Handle item clicks
                    setOnClickListener(object : Adaptor.OnClickListener {
                        override fun onClick(position: Int, model: WeatherCityList) {
                            val intent = Intent(this@WeatherActivity, WeatherDetail::class.java).apply {
                                putExtra(DETAILS, model)
                            }
                            startActivity(intent)
                        }
                    })
                    setOnClickListenerButton(object : Adaptor.OnClickListenerButton {
                        override fun onBackClick() {
                            val intent = Intent(this@WeatherActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    })
                }
                recyclerView.adapter = weatherAdapter
            }
        }









    }

    companion object {
        const val DETAILS = "details"
    }
}
