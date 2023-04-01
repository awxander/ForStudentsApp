package com.example.forstudents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.forstudents.databinding.ActivityLoginBinding
import com.example.forstudents.ui.LoginRegisterViewModel
import com.example.forstudents.ui.LoginRegisterViewModelFactory
import com.example.forstudents.ui.RegisterLoginState

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginRegisterViewModel: LoginRegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginRegisterViewModel = ViewModelProvider(
            this,
            LoginRegisterViewModelFactory()
        )[LoginRegisterViewModel::class.java]
        setContentView(binding.root)
        setObservers()
    }

    private fun setObservers(){
        loginRegisterViewModel.state.observe(this@LoginActivity, ::handleRegisterLoginState)
    }

    private fun handleRegisterLoginState(state : RegisterLoginState){

    }



}