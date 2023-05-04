package com.example.forstudents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forstudents.data.repository.ForStudentsRepositoryImpl
import com.example.forstudents.domain.usecase.LoginUseCase
import com.example.forstudents.domain.usecase.RegisterUseCase
import com.example.forstudents.presentation.viewmodel.LoginRegisterViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginRegisterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginRegisterViewModel::class.java)) {
            val repository = ForStudentsRepositoryImpl()
            return LoginRegisterViewModel(
                LoginUseCase(repository),
                RegisterUseCase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}