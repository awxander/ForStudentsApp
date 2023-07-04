package com.example.forstudents.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import com.example.forstudents.domain.usecase.LoadQuestionsUseCase
import com.example.forstudents.presentation.state.LoadQuestionsState
import com.example.forstudents.presentation.state.QuestionState
import com.example.forstudents.util.MIN_QUESTION_LENGTH
import com.example.forstudents.util.MIN_TOPIC_LENGTH
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val askQuestionUseCase: AskQuestionUseCase,
    private val loadQuestionsUseCase: LoadQuestionsUseCase
) : ViewModel() {

    private val _questionState: MutableLiveData<QuestionState> =
        MutableLiveData(QuestionState.Initial)
    val questionState: LiveData<QuestionState> = _questionState

    private val _loadQuestionsState: MutableLiveData<LoadQuestionsState> =
        MutableLiveData(LoadQuestionsState.Initial)
    val loadQuestionsState: LiveData<LoadQuestionsState> = _loadQuestionsState

    fun ask(questionModel: QuestionModel) {
        viewModelScope.launch {
            _questionState.value = QuestionState.Loading
            try {
                checkQuestionsValid(questionModel)
                askQuestionUseCase.execute(questionModel)
                _questionState.value = QuestionState.Success
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _questionState.value = QuestionState.Error(ex.message.orEmpty())
            }
        }
    }

    private fun checkQuestionsValid(questionModel: QuestionModel) {
        questionModel.apply {
            if (body.length < MIN_QUESTION_LENGTH || topic.length < MIN_TOPIC_LENGTH)
                throw java.lang.IllegalArgumentException("that small... question?\n try again brother")
        }
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _loadQuestionsState.value = LoadQuestionsState.Loading
            try {
                val questions = loadQuestionsUseCase.execute()
                _loadQuestionsState.value = LoadQuestionsState.Content(questions)
            } catch (ex: Exception) {
                _loadQuestionsState.value = LoadQuestionsState.Error(ex.message.orEmpty())
            }
        }
    }
}