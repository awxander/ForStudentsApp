package com.example.forstudents.data.remote

import com.example.forstudents.data.model.TokenModel
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ForStudentsApi {

    @POST("login")
    suspend fun login(@Body userSignInModel: UserLoginModel): TokenModel

    @POST("register")
    suspend fun register(@Body userRegisterModel: UserRegisterModel): TokenModel

}