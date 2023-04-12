package com.example.forstudents.data.model

import com.google.gson.annotations.SerializedName

data class QuestionModel(
    @SerializedName("topic") val topic : String,
    @SerializedName("body") val body : String
)