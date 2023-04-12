package com.example.forstudents.presentsion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class QuestionViewModel(private val askQuestionUseCase: AskQuestionUseCase) : ViewModel() {

    private val _questionState: MutableLiveData<QuestionState> = MutableLiveData(QuestionState.Initial)
    val questionState: LiveData<QuestionState> = _questionState

    fun ask(questionModel: QuestionModel){
        viewModelScope.launch {
            _questionState.value = QuestionState.Loading

            try {
                askQuestionUseCase.execute(questionModel)
                _questionState.value = QuestionState.Success
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _questionState.value = QuestionState.Error(ex.message)
            }
        }
    }


}