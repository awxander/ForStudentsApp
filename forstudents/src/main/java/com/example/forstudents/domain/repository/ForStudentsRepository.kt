package com.example.forstudents.domain.repository

import com.example.forstudents.data.model.*
import retrofit2.http.Body

interface ForStudentsRepository {

    suspend fun loginUser(userLoginModel: UserLoginModel) : TokenModel

    suspend fun registerUser(userRegisterModel: UserRegisterModel) : TokenModel

    suspend fun askQuestion(questionModel: QuestionModel)

    suspend fun loadQuestions() : List<IncomingQuestionModel>
}