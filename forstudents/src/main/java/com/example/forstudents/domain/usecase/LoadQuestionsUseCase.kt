package com.example.forstudents.domain.usecase

import com.example.forstudents.domain.repository.ForStudentsRepository

class LoadQuestionsUseCase(private val repository: ForStudentsRepository) {
    suspend fun execute() =
        repository.loadQuestions()
}