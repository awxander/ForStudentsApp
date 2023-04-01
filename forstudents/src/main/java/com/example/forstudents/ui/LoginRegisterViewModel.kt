package com.example.forstudents.ui


import android.util.Log
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel

import com.example.forstudents.data.repository.LoginRepository
import com.example.forstudents.util.TAG
import kotlinx.coroutines.launch

class LoginRegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _state: MutableLiveData<RegisterLoginState> = MutableLiveData(RegisterLoginState.Initial)

    val state: LiveData<RegisterLoginState> = _state

    fun login(userLoginModel: UserLoginModel){
        viewModelScope.launch {
            _state.value = RegisterLoginState.Loading
            try {
                val token = loginRepository.loginUser(userLoginModel)
                Log.i(javaClass.simpleName, "got info")
                _state.value = RegisterLoginState.Content(token)
            } catch (e: Exception) {
                handleException(e)
            }
            _state.value = RegisterLoginState.Initial
        }
    }

    private fun handleException(e: Exception){
        Log.e(TAG, e.message.orEmpty())
        _state.value = RegisterLoginState.Error(e.message.orEmpty())
    }

    fun register(userRegisterModel: UserRegisterModel){
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _state.value = RegisterLoginState.Error("not a valid username ")
        } else if (!isPasswordValid(password)) {
            _state.value = RegisterLoginState.Error("password in not valid ")
        }
    }



    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}