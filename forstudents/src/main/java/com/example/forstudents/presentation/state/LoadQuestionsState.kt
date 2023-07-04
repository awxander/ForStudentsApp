package com.example.forstudents.presentation.state

import com.example.forstudents.data.model.IncomingQuestionModel

sealed interface LoadQuestionsState{
    object Initial: LoadQuestionsState

    object Loading: LoadQuestionsState

    data class Content(val questions: List<IncomingQuestionModel>) : LoadQuestionsState

    data class Error(val text: String) : LoadQuestionsState
}