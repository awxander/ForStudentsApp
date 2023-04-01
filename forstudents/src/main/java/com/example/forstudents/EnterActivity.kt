package com.example.forstudents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forstudents.databinding.ActivityEnterBinding

class EnterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEnterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)
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