
package com.example.assignment
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.WeatherActivity.Companion.DETAILS
import com.example.assignment.models.WeatherCityList
import com.example.assignment.viewmodels.DetailsViewModel
import java.time.LocalDateTime

class WeatherDetail : AppCompatActivity() {
    private val weatherViewModel: DetailsViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather_detail)

//        val temperature: TextView = findViewById(R.id.temp_view)
//        val maxTemperature: TextView = findViewById(R.id.max_temp_view)
//        val minTemperature: TextView = findViewById(R.id.min_temp_view)
//        val main: TextView = findViewById(R.id.main_view)
//        val description: TextView = findViewById(R.id.description_view)
//        val image = findViewById<ImageView>(R.id.weather_icon)
//        val button = findViewById<Button>(R.id.backButton)

        //val cityName = findViewById<TextView>(R.id.city_name)
        val city: TextView = findViewById(R.id.city_name)
        val icon: ImageView = findViewById(R.id.icon)
        //val condition: TextView = findViewById(R.id.condition)
        val temperature: TextView = findViewById(R.id.temperature)
        val maxTemp: TextView = findViewById(R.id.max_temp)
        val mintemp: TextView = findViewById(R.id.min_temp)
        val day:TextView = findViewById(R.id.day)
        val date: TextView = findViewById(R.id.date)
        val time:TextView = findViewById(R.id.time)
        val humidity: TextView = findViewById(R.id.humidity_text)
        val pressure: TextView = findViewById(R.id.pressure_text)
        val data = intent.getSerializableExtra(DETAILS) as WeatherCityList

        weatherViewModel.setDetails(data)
        weatherViewModel.weatherSharedData.observe(this) { currWeather ->
            currWeather?.let {
                Log.d("MAIN",it.city.toString())
                val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val dateTime = LocalDateTime.parse(currWeather.dt_txt, formatter)
                val dateTemp = dateTime.toLocalDate()
                val timeTemp = dateTime.toLocalTime()
                val dayOfWeek = dateTemp.dayOfWeek

                val tempMax = currWeather.main.temp_max
                val formattedTempMax = String.format("%.1f", tempMax)
                val tempMin = currWeather.main.temp_min
                val formattedTempMin = String.format("%.1f", tempMin)
                val temp = currWeather.main.temp
                val formattedTemp = String.format("%.1f", temp)
                city.text = currWeather.city
                day.text = dayOfWeek.toString()
                date.text = dateTemp.toString()
                time.text = timeTemp.toString()
                when(currWeather.weather[0].main){
                    "Rain" -> icon.setImageResource(R.drawable.rainy)
                    "Clouds" -> icon.setImageResource(R.drawable.cloudy)
                    else -> icon.setImageResource(R.drawable.clear)
                }
                temperature.text = "$formattedTemp °C"
                maxTemp.text = "$formattedTempMax °C"
                mintemp.text = "$formattedTempMin °C"
                humidity.text = "${currWeather.main.humidity} %"
                pressure.text = "${currWeather.main.pressure} hPa"
            }
        }
        val button = findViewById<ImageButton>(R.id.back_button_detail)
        button.setOnClickListener {
            backEvent()
        }
    }


    private fun backEvent(){
        finish()
    }

}



// package com.example.assignment
// import android.annotation.SuppressLint
// import android.content.Intent
// import android.os.Bundle
// import android.widget.Button
// import android.widget.ImageView
// import android.widget.TextView
// import androidx.activity.enableEdgeToEdge
// import androidx.activity.viewModels
// import androidx.appcompat.app.AppCompatActivity
// import com.example.assignment.WeatherActivity.Companion.DETAILS
// import com.example.assignment.models.WeatherCityList
// import com.example.assignment.viewmodels.DetailsViewModel

// class WeatherDetail : AppCompatActivity() {
//     private val weatherViewModel: DetailsViewModel by viewModels()

//     @SuppressLint("SetTextI18n")
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         enableEdgeToEdge()
//         setContentView(R.layout.activity_weather_detail)

//         val temperature: TextView = findViewById(R.id.temp_view)
//         val maxTemperature: TextView = findViewById(R.id.max_temp_view)
//         val minTemperature: TextView = findViewById(R.id.min_temp_view)
//         val main: TextView = findViewById(R.id.main_view)
//         val description: TextView = findViewById(R.id.description_view)
//         val image = findViewById<ImageView>(R.id.weather_icon)
//         val button = findViewById<Button>(R.id.backButton)
//         val data = intent.getSerializableExtra(DETAILS) as WeatherCityList

//         weatherViewModel.setDetails(data)
//         weatherViewModel.weatherSharedData.observe(this) { currWeather ->
//             currWeather?.let {
//                 val imgType = it.weather[0].main.lowercase()
//                 when (imgType) {
//                     "rain" -> image.setImageResource(R.drawable.rain)
//                     "clouds" -> image.setImageResource(R.drawable.clouds)
//                     else -> image.setImageResource(R.drawable.sun)
//                 }
//                 temperature.text = "Temperature: ${it.main.temp}"
//                 maxTemperature.text = "Maximum Temperature: ${it.main.temp_max}"
//                 minTemperature.text = "Minimum Temperature: ${it.main.temp_min}"
//                 main.text = "Weather Condition: ${it.weather[0].main}"
//                 description.text = "Weather Description: ${it.weather[0].description}"
//             }
//         }
//         button.setOnClickListener {
//             finish()
//         }
//     }

// }
