package com.example.forstudents.data.model

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(
    @SerializedName("email") val email : String,
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
)