package com.example.forstudents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle import androidx.appcompat.app.AppCompatDelegate
import com.example.forstudents.databinding.ActivityEnterBinding

class EnterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEnterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setButtonsListeners()
    }

    private fun setButtonsListeners(){
        binding.apply {
            goLoginBtn.setOnClickListener {
                startLoginActivity()
            }

            goRegisterBtn.setOnClickListener {
                startRegisterActivity()
            }
        }
    }

    private fun startLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun startRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}