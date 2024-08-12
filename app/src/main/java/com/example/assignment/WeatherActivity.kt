package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.models.WeatherCityList
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.example.assignment.networkcalls.Response
import com.example.assignment.viewmodels.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class WeatherActivity : AppCompatActivity() {
    private lateinit var weatherAdapter: Adaptor
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var sharedPrefObj: SharedPreferences
    @SuppressLint("MissingInflatedId", "WrongViewCast", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        val city = intent.getStringExtra("city")
        val apiKey = "aea518baa59e62768dc526804734c47d"



        sharedPrefObj = getSharedPreferences("weather_cache", Context.MODE_PRIVATE)
        val editor = sharedPrefObj.edit()
        val gson = Gson()
        //val cacheData = sharedPrefObj.getString("weather_list",null)
        val cacheData = sharedPrefObj.getString(city!!.lowercase(),null)
        if(cacheData!=null){
            val weatherList = gson.fromJson(cacheData,OneDayWeatherList::class.java)
            weatherList?.let {
                recyclerView(weatherList,city)
            }
        }
        else{
            city.let {
                weatherViewModel.fetchData(it, apiKey)
            }
        }
        weatherViewModel.weatherSharedData.observe(this) { it ->
            when(it) {
                is Response.Success -> {
                    it.data?.let {
                        val data = gson.toJson(it)
                        editor.putString(city.lowercase(),data)
                        editor.apply()
                        recyclerView(it,city)
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this,it.errorMessage,Toast.LENGTH_LONG).show()
                    finish()
                }
                else ->{
                    Toast.makeText(this,"Loading Some Data",Toast.LENGTH_LONG).show()
                }
            }
        }
        val button = findViewById<ImageButton>(R.id.back_button)
        button.setOnClickListener {
            backEvent()
        }
    }

    private fun backEvent(){
        finish()
    }
    companion object {
        const val DETAILS = "details"
    }

    private fun recyclerView(detail: OneDayWeatherList, city: String){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        weatherAdapter = Adaptor(detail,city, this).apply {
            setOnClickListener(object : Adaptor.OnClickListener {
                override fun onClick(position: Int, model: WeatherCityList) {
                    Log.d("MAIN",model.city.toString())
                    val intent = Intent(this@WeatherActivity, WeatherDetail::class.java).apply {
                        putExtra(DETAILS, model)
                    }
                    startActivity(intent)
                }
            })
//                            setOnClickListenerButton(object : Adaptor.OnClickListenerButton {
//                                override fun onBackClick() {
//                                    finish()
//                                }
//                            })
        }
        recyclerView.adapter = weatherAdapter
    }
}


// package com.example.assignment

// import android.content.Intent
// import android.os.Bundle
// import android.util.Log
// import android.view.View
// import android.widget.Toast
// import androidx.appcompat.app.AlertDialog
// import androidx.appcompat.app.AppCompatActivity
// import androidx.lifecycle.ViewModelProvider
// import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.RecyclerView
// import com.example.assignment.models.WeatherCityList
// import com.example.assignment.models.onedayresponse.OneDayWeatherList
// import com.example.assignment.networkcalls.Response
// import com.example.assignment.viewmodels.WeatherViewModel
// import com.google.android.material.snackbar.Snackbar

// class WeatherActivity : AppCompatActivity() {
//     private lateinit var weatherAdapter: Adaptor
//     private lateinit var weatherViewModel: WeatherViewModel
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         setContentView(R.layout.activity_weather)
//         weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

//         val city = intent.getStringExtra("city")
//         val apiKey = "aea518baa59e62768dc526804734c47d"

//         val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//         recyclerView.layoutManager = LinearLayoutManager(this)

//         city?.let {
//             weatherViewModel.fetchData(it, apiKey)
//         }
//         Log.d("MAIN", weatherViewModel.check.toString())
//         if(weatherViewModel.check == 0){
//             val intent = Intent(this@WeatherActivity, MainActivity::class.java)
//             startActivity(intent)
//         }

//         weatherViewModel.weatherSharedData.observe(this) { it ->
//             when(it) {
//                 is Response.Success -> {
//                     it.data?.let {
//                         weatherAdapter = Adaptor(it, this).apply {
//                             setOnClickListener(object : Adaptor.OnClickListener {
//                                 override fun onClick(position: Int, model: WeatherCityList) {
//                                     val intent = Intent(this@WeatherActivity, WeatherDetail::class.java).apply {
//                                         putExtra(DETAILS, model)
//                                     }
//                                     startActivity(intent)
//                                 }
//                             })
//                             setOnClickListenerButton(object : Adaptor.OnClickListenerButton {
//                                 override fun onBackClick() {
//                                     finish()
//                                 }
//                             })
//                         }
//                         recyclerView.adapter = weatherAdapter
//                     }
//                 }

//                 is Response.Error -> {
//                     Toast.makeText(this,it.errorMessage,Toast.LENGTH_LONG).show()
//                     finish()
//                 }
//                 is Response.Loading -> {
//                     Toast.makeText(this,"Loading Some Data",Toast.LENGTH_LONG).show()
//                 }
//             }
//         }
//     }

//     companion object {
//         const val DETAILS = "details"
//     }
// }
