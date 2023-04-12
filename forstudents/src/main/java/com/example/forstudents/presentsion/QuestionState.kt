package com.example.forstudents.presentsion


sealed interface QuestionState {

    object Initial: QuestionState

    object Loading: QuestionState

    object Success: QuestionState

    data class Error(val text: String?) : QuestionState
}