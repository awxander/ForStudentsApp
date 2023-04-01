package com.example.forstudents.ui


import android.util.Log
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.forstudents.R
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel

import com.example.forstudents.data.repository.LoginRepository
import com.example.forstudents.util.TAG
import kotlinx.coroutines.launch

class LoginRegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _registerLoginState: MutableLiveData<RegisterLoginState> = MutableLiveData(RegisterLoginState.Initial)
    private val _formState: MutableLiveData<FormState> = MutableLiveData()

    val registerLoginState: LiveData<RegisterLoginState> = _registerLoginState
    val formState: LiveData<FormState> = _formState

    fun login(userLoginModel: UserLoginModel){
        viewModelScope.launch {
            _registerLoginState.value = RegisterLoginState.Loading
            try {
                val token = loginRepository.loginUser(userLoginModel)
                Log.i(javaClass.simpleName, "got info")
                _registerLoginState.value = RegisterLoginState.Content(token)
            } catch (e: Exception) {
                handleException(e)
            }
            _registerLoginState.value = RegisterLoginState.Initial
        }
    }

    private fun handleException(e: Exception){
        Log.e(TAG, e.message.orEmpty())
        _registerLoginState.value = RegisterLoginState.Error(e.message.orEmpty())
    }

    fun register(userRegisterModel: UserRegisterModel){
    }


    fun loginDataChanged(email: String, password: String) {
        if (!isUserNameValid(email)) {
            _formState.value = FormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _formState.value = FormState(passwordError = R.string.invalid_password)
        } else {
            _formState.value = FormState(isDataValid = true)
        }
    }



    // A placeholder username validation check
    private fun isUserNameValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}