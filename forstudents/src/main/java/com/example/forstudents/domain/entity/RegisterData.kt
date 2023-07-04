package com.example.forstudents.domain.entity

data class RegisterData(
    val email: String,
    val username: String,
    val password: String,
    val passwordCopy : String
)
