package com.example.forstudents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.forstudents.data.model.UserRegisterModel
import com.example.forstudents.databinding.ActivityRegisterBinding
import com.example.forstudents.presentation.*
import com.example.forstudents.presentation.viewmodel.LoginRegisterViewModel
import com.example.forstudents.util.afterTextChanged

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginRegisterViewModel: LoginRegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            signUpBtn.setOnClickListener {
                loginRegisterViewModel.register(
                    UserRegisterModel(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        username = usernameEditText.text.toString()
                    )
                )
            }

            emailEditText.afterTextChanged {
                loginRegisterViewModel.registerDataChanged(
                    RegisterData(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        username = usernameEditText.text.toString(),
                        passwordCopy = repeatPasswordEditText.text.toString(),
                    )
                )
            }

            passwordEditText.afterTextChanged {
                loginRegisterViewModel.registerDataChanged(
                    RegisterData(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        username = usernameEditText.text.toString(),
                        passwordCopy = repeatPasswordEditText.text.toString(),
                    )
                )
            }

            repeatPasswordEditText.afterTextChanged {
                loginRegisterViewModel.registerDataChanged(
                    RegisterData(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        username = usernameEditText.text.toString(),
                        passwordCopy = repeatPasswordEditText.text.toString(),
                    )
                )
            }

            usernameEditText.afterTextChanged {
                loginRegisterViewModel.registerDataChanged(
                    RegisterData(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        username = usernameEditText.text.toString(),
                        passwordCopy = repeatPasswordEditText.text.toString(),
                    )
                )
            }
        }
    }

    private fun setObservers() {
        loginRegisterViewModel.registerLoginState.observe(
            this@RegisterActivity,
            ::handleRegisterState
        )
        loginRegisterViewModel.formState.observe(this@RegisterActivity, ::handleFormState)
    }

    private fun handleRegisterState(state: RegisterLoginState) {
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

            signUpBtn.isEnabled = state.isDataValid

            if (state.emailError != null) {
                emailEditText.error = getString(state.emailError)
            }
            if (state.usernameError != null) {
                usernameEditText.error = getString(state.usernameError)
            }
            if (state.reenteredPasswordError != null) {
                repeatPasswordEditText.error = getString(state.reenteredPasswordError)
            }

            if (state.passwordError != null) {
                passwordEditText.error = getString(state.passwordError)
            }
        }
    }


}