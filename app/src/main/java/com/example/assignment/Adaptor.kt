package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.i18n.DateTimeFormatter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.models.WeatherCityList
import com.example.assignment.models.onedayresponse.OneDayWeatherList
import com.squareup.picasso.Picasso
import java.time.LocalDateTime

class Adaptor(
    private var list: OneDayWeatherList,
    private var cityName: String?,
    private val context: Context
) : RecyclerView.Adapter<Adaptor.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    //private var buttonListener: OnClickListenerButton? = null // Properly initialized buttonListener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val city: TextView = itemView.findViewById(R.id.city_name)
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val condition: TextView = itemView.findViewById(R.id.condition)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val maxTemp: TextView = itemView.findViewById(R.id.max_temp)
        val mintemp: TextView = itemView.findViewById(R.id.min_temp)
        val day:TextView = itemView.findViewById(R.id.day)
        val date: TextView = itemView.findViewById(R.id.date)
        val time:TextView = itemView.findViewById(R.id.time)
        //val backButton: Button = itemView.findViewById(R.id.backButton) // Renamed for clarity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.oneDayList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currWeather = list.oneDayList[position]
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
        holder.apply {
            city.text = cityName
//            val iconUrl = "http://openweathermap.org/img/wn/${currWeather.weather[0].icon}@4x.png"
//            Picasso.get()
//                .load(iconUrl)
//                .into(icon)

            condition.text = currWeather.weather[0].main
            temperature.text = "$formattedTemp °C"
            maxTemp.text = "$formattedTempMax °C"
            mintemp.text = "$formattedTempMin °C"
            day.text = dayOfWeek.toString()
            date.text = dateTemp.toString()
            time.text = timeTemp.toString()

            when(currWeather.weather[0].main){
                "Rain" -> icon.setImageResource(R.drawable.rainy)
                "Clouds" -> icon.setImageResource(R.drawable.cloudy)
                else -> icon.setImageResource(R.drawable.sun)
            }
//            temperature.text = "Temperature: ${currWeather.main.temp}"
//            maxTemperature.text = "Max Temp: ${currWeather.main.temp_max}"
//            minTemperature.text = "Min Temp: ${currWeather.main.temp_min}"
//            main.text = "Condition: ${currWeather.weather[0].main}"
//            description.text = "Description: ${currWeather.weather[0].description}"

            itemView.setOnClickListener {
                onClickListener?.onClick(position, currWeather)
            }
//            backButton.setOnClickListener {
//                buttonListener?.onBackClick() // Updated method name for better clarity
//            }
        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.onClickListener = listener
    }
//    fun setOnClickListenerButton(listener: OnClickListenerButton) {
//        this.buttonListener = listener // Corrected assignment
//    }
    interface OnClickListener {
        fun onClick(position: Int, model: WeatherCityList)
    }
//    interface OnClickListenerButton {
//        fun onBackClick() // Updated method name for clarity
//    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun updateData(newList: OneDayWeatherList) {
//        list = newList
//        notifyDataSetChanged()
//    }
}
