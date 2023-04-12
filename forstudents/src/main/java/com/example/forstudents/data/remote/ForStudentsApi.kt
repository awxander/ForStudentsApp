package com.example.forstudents.data.remote

import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.data.model.TokenModel
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


//TODO, убрать, дергать токен из db
private const val TOKEN = "Authorization: Bearer 8ddf1647-09d8-4850-a99a-a9dba5c737c8"

interface ForStudentsApi {

    @POST("login")
    suspend fun login(@Body userSignInModel: UserLoginModel): TokenModel

    @POST("register")
    suspend fun register(@Body userRegisterModel: UserRegisterModel): TokenModel

    @Headers(TOKEN)
    @POST("questions/ask")
    suspend fun askQuestion(@Body questionModel: QuestionModel)

}