package com.example.forstudents.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IncomingQuestionModel(
    @SerializedName("email") val email : String,
    @SerializedName("username") val username : String,
    @SerializedName("topic") val topic : String,
    @SerializedName("body") val body : String
) : Parcelable