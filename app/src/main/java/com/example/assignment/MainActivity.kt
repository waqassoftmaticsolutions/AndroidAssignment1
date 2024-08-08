package com.example.assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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
    }
}