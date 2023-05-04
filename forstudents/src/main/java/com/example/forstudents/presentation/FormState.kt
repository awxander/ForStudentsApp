package com.example.forstudents.presentation

data class FormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val usernameError: Int? = null,
    val reenteredPasswordError: Int? = null,
    val isDataValid: Boolean = false
)