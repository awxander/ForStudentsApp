package com.example.forstudents.data.model

import com.google.gson.annotations.SerializedName

data class IncomingQuestionModel(
    @SerializedName("email") val email : String,
    @SerializedName("username") val username : String,
    @SerializedName("topic") val topic : String,
    @SerializedName("body") val body : String
)