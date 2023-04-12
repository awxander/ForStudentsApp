package com.example.forstudents.domain.repository

import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.data.model.TokenModel
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel
import retrofit2.http.Body

interface ForStudentsRepository {

    suspend fun loginUser(userLoginModel: UserLoginModel) : TokenModel

    suspend fun registerUser(userRegisterModel: UserRegisterModel) : TokenModel

    suspend fun askQuestion(@Body questionModel: QuestionModel)
}