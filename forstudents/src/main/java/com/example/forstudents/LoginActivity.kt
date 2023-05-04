package com.example.forstudents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.databinding.ActivityLoginBinding
import com.example.forstudents.presentation.FormState
import com.example.forstudents.presentation.viewmodel.LoginRegisterViewModel
import com.example.forstudents.presentation.LoginRegisterViewModelFactory
import com.example.forstudents.presentation.RegisterLoginState
import com.example.forstudents.util.afterTextChanged

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
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
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            signInBtn.setOnClickListener {
                loginRegisterViewModel.login(
                    UserLoginModel(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                )
            }
            emailEditText.afterTextChanged {
                loginRegisterViewModel.loginDataChanged(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                )
            }
            passwordEditText.afterTextChanged {
                loginRegisterViewModel.loginDataChanged(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
    }

    private fun setObservers() {
        loginRegisterViewModel.registerLoginState.observe(
            this@LoginActivity,
            ::handleLoginState
        )
        loginRegisterViewModel.formState.observe(this@LoginActivity, ::handleFormState)
    }

    private fun handleLoginState(state: RegisterLoginState) {
        when (state) {
            RegisterLoginState.Initial -> Unit
            RegisterLoginState.Loading -> Unit //TODO add progress bar
            is RegisterLoginState.Content -> {
                startMainActivity()
            }
            is RegisterLoginState.Error -> showErrorMsg(state.text)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun showErrorMsg(errorMsg: String) {
        binding.tvErrorMsg.apply {
            text = errorMsg
            visibility = View.VISIBLE
        }
    }

    private fun handleFormState(state: FormState) {
        binding.apply {

            signInBtn.isEnabled = state.isDataValid

            if (state.emailError != null) {
                emailEditText.error = getString(state.emailError)
            }
            if (state.passwordError != null) {
                passwordEditText.error = getString(state.passwordError)
            }
        }
    }


}