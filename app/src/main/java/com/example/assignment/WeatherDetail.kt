
package com.example.assignment
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.WeatherActivity.Companion.DETAILS
import com.example.assignment.models.WeatherCityList
import com.example.assignment.viewmodels.DetailsViewModel

class WeatherDetail : AppCompatActivity() {
    private val weatherViewModel: DetailsViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather_detail)

        val temperature: TextView = findViewById(R.id.temp_view)
        val maxTemperature: TextView = findViewById(R.id.max_temp_view)
        val minTemperature: TextView = findViewById(R.id.min_temp_view)
        val main: TextView = findViewById(R.id.main_view)
        val description: TextView = findViewById(R.id.description_view)
        val image = findViewById<ImageView>(R.id.weather_icon)
        val button = findViewById<Button>(R.id.backButton)
        val data = intent.getSerializableExtra(DETAILS) as WeatherCityList

        weatherViewModel.setDetails(data)
        weatherViewModel.weatherSharedData.observe(this) { currWeather ->
            currWeather?.let {
                val imgType = it.weather[0].main.lowercase()
                when (imgType) {
                    "rain" -> image.setImageResource(R.drawable.rain)
                    "clouds" -> image.setImageResource(R.drawable.clouds)
                    else -> image.setImageResource(R.drawable.sun)
                }
                temperature.text = "Temperature: ${it.main.temp}"
                maxTemperature.text = "Maximum Temperature: ${it.main.temp_max}"
                minTemperature.text = "Minimum Temperature: ${it.main.temp_min}"
                main.text = "Weather Condition: ${it.weather[0].main}"
                description.text = "Weather Description: ${it.weather[0].description}"
            }
        }
        button.setOnClickListener {
            finish()
        }
    }

}
