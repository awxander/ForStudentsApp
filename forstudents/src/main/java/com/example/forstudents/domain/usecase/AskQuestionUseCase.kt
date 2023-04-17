package com.example.forstudents.domain.usecase

import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.domain.repository.ForStudentsRepository

class AskQuestionUseCase(private val repository: ForStudentsRepository) {

    suspend fun execute(questionModel: QuestionModel): Unit =
        repository.askQuestion(questionModel)
}