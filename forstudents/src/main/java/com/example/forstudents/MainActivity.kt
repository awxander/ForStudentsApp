package com.example.forstudents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forstudents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }
}