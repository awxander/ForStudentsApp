package com.example.forstudents.domain.usecase

import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.data.model.TokenModel
import com.example.forstudents.data.model.UserRegisterModel
import com.example.forstudents.domain.repository.ForStudentsRepository

class RegisterUseCase(private val repository: ForStudentsRepository) {
    suspend fun execute(userRegisterModel: UserRegisterModel): TokenModel =
        repository.registerUser(userRegisterModel)
}