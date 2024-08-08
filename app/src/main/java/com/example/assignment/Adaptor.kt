package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.models.WeatherCityList
import com.example.assignment.models.onedayresponse.OneDayWeatherList

class Adaptor(
    private var list: OneDayWeatherList,
    private val context: Context
) : RecyclerView.Adapter<Adaptor.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var buttonListener: OnClickListenerButton? = null // Properly initialized buttonListener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperature: TextView = itemView.findViewById(R.id.temp_view)
        val maxTemperature: TextView = itemView.findViewById(R.id.max_temp_view)
        val minTemperature: TextView = itemView.findViewById(R.id.min_temp_view)
        val main: TextView = itemView.findViewById(R.id.main_view)
        val description: TextView = itemView.findViewById(R.id.description_view)
        val backButton: Button = itemView.findViewById(R.id.backButton) // Renamed for clarity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.oneDayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currWeather = list.oneDayList[position]
        holder.apply {
            temperature.text = "Temperature: ${currWeather.main.temp}"
            maxTemperature.text = "Max Temp: ${currWeather.main.temp_max}"
            minTemperature.text = "Min Temp: ${currWeather.main.temp_min}"
            main.text = "Condition: ${currWeather.weather[0].main}"
            description.text = "Description: ${currWeather.weather[0].description}"

            itemView.setOnClickListener {
                onClickListener?.onClick(position, currWeather)
            }
            backButton.setOnClickListener {
                buttonListener?.onBackClick() // Updated method name for better clarity
            }
        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.onClickListener = listener
    }
    fun setOnClickListenerButton(listener: OnClickListenerButton) {
        this.buttonListener = listener // Corrected assignment
    }
    interface OnClickListener {
        fun onClick(position: Int, model: WeatherCityList)
    }
    interface OnClickListenerButton {
        fun onBackClick() // Updated method name for clarity
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: OneDayWeatherList) {
        list = newList
        notifyDataSetChanged()
    }
}
