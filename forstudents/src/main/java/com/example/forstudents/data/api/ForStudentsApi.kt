package com.example.forstudents.data.api

import com.example.forstudents.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

//TODO, убрать, дергать токен из db
//token для sashok.tsibin@gmail.com Alexander2001
private const val TOKEN = "Authorization: e81b1e05-db2a-45e9-b158-bd2c570fcd8b"

interface ForStudentsApi {

    @POST("login")
    suspend fun login(@Body userSignInModel: UserLoginModel): TokenModel

    @POST("register")
    suspend fun register(@Body userRegisterModel: UserRegisterModel): TokenModel

    @Headers(TOKEN)
    @POST("questions/new")
    suspend fun askQuestion(@Body questionModel: QuestionModel)

    @Headers(TOKEN)
    @GET("questions/all")
    suspend fun loadQuestions() : List<IncomingQuestionModel>

}