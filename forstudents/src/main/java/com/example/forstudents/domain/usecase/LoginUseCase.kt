package com.example.forstudents.domain.usecase

import com.example.forstudents.data.model.TokenModel
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.domain.repository.ForStudentsRepository

class LoginUseCase(private val repository: ForStudentsRepository) {

    suspend fun execute(userLoginModel: UserLoginModel): TokenModel =
        repository.loginUser(userLoginModel)
}