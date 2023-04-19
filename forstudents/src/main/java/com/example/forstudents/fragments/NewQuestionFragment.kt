package com.example.forstudents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.forstudents.MainActivity
import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.databinding.FragmentNewQuestionBinding
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import com.example.forstudents.presentsion.QuestionState
import com.example.forstudents.presentsion.QuestionViewModel
import com.example.forstudents.util.printBackStackInLog

class NewQuestionFragment : Fragment() {

    private lateinit var binding: FragmentNewQuestionBinding
    private val navController get() = findNavController()
    private val viewModel by lazy{
        QuestionViewModel(AskQuestionUseCase((activity as MainActivity).repository))
    }
//TODO убрать данный вызов, добавить di и дергать через viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewQuestionBinding.inflate(inflater)
        setListeners()
        setObservers()
        //TODO пофиксить хуйню с
        // закрытием приложения на back
        printBackStackInLog()
        return binding.root
    }

    private fun setObservers() {
        viewModel.questionState.observe(viewLifecycleOwner, ::handleQuestionState)
    }

    private fun showErrorMessage(message:String ){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun handleQuestionState(state: QuestionState) {
        when (state) {
            is QuestionState.Error -> {
                showErrorMessage(state.text.toString())
                binding.askButton.isEnabled = true
            }
            QuestionState.Initial -> {
            }
            QuestionState.Loading -> {
            }//TODO() добавить progress bar
            QuestionState.Success -> {
                finish()
            }
        }
    }

    private fun finish(){
        parentFragmentManager.popBackStack()
    }

    private fun setListeners() {
        binding.apply {
            askButton.setOnClickListener {
                val questionBody = editQuestion.text.toString()
                val topic = editTopic.text.toString()
                viewModel.ask(QuestionModel(topic = topic, body = questionBody))
                it.isEnabled = false
            }
        }
    }


}