package com.example.forstudents.presentsion


import android.util.Log
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.forstudents.R
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel
import com.example.forstudents.domain.usecase.LoginUseCase
import com.example.forstudents.domain.usecase.RegisterUseCase

import com.example.forstudents.util.TAG
import kotlinx.coroutines.launch

class LoginRegisterViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerLoginState: MutableLiveData<RegisterLoginState> = MutableLiveData(RegisterLoginState.Initial)
    val registerLoginState: LiveData<RegisterLoginState> = _registerLoginState

    private val _formState: MutableLiveData<FormState> = MutableLiveData()
    val formState: LiveData<FormState> = _formState

    fun login(userLoginModel: UserLoginModel){
        viewModelScope.launch {
            _registerLoginState.value = RegisterLoginState.Loading
            try {
                val token = loginUseCase.execute(userLoginModel)
                Log.i(javaClass.simpleName, "successfully got token")
                _registerLoginState.value = RegisterLoginState.Content(token)
            } catch (e: Exception) {
                handleException(e)
            }
            _registerLoginState.value = RegisterLoginState.Initial
        }
    }

    private fun handleException(e: Exception){
        Log.e(TAG,  e.message.orEmpty() +"\n" + e.stackTraceToString() )
        _registerLoginState.value = RegisterLoginState.Error(e.message.orEmpty())
    }

    fun register(userRegisterModel: UserRegisterModel){
        viewModelScope.launch {
            _registerLoginState.value = RegisterLoginState.Loading
            try {
                val token = registerUseCase.execute(userRegisterModel)
                Log.i(javaClass.simpleName, "successfully got token")
                _registerLoginState.value = RegisterLoginState.Content(token)
            } catch (e: Exception) {
                handleException(e)
            }
            _registerLoginState.value = RegisterLoginState.Initial
        }
    }

    fun registerDataChanged(registerData: RegisterData){
        if (!isEmailValid(registerData.email)) {
            _formState.value = FormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(registerData.password)) {
            _formState.value = FormState(passwordError = R.string.invalid_password)
        } else if (!isUserNameValid(registerData.username)){
            _formState.value = FormState(usernameError = R.string.invalid_username)
        } else if(registerData.password != registerData.passwordCopy){
            _formState.value = FormState(reenteredPasswordError = R.string.different_passwords)
        } else {
            _formState.value = FormState(isDataValid = true)
        }
    }


    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _formState.value = FormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _formState.value = FormState(passwordError = R.string.invalid_password)
        } else {
            _formState.value = FormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String) : Boolean{
        return username.isNotBlank()
    }



    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            false
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}