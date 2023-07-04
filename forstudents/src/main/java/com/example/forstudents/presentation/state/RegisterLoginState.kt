package com.example.forstudents.presentation.state

import com.example.forstudents.data.model.TokenModel

sealed interface RegisterLoginState {

    object Initial: RegisterLoginState

    object Loading: RegisterLoginState

    data class Content(val token: TokenModel) : RegisterLoginState

    data class Error(val text: String) : RegisterLoginState

}