package com.example.forstudents.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.forstudents.MainActivity
import com.example.forstudents.appComponent
import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.databinding.FragmentNewQuestionBinding
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import com.example.forstudents.domain.usecase.LoadQuestionsUseCase
import com.example.forstudents.presentation.QuestionState
import com.example.forstudents.presentation.viewmodel.QuestionViewModel
import com.example.forstudents.util.printBackStackInLog
import javax.inject.Inject

class NewQuestionFragment : Fragment() {

    private var _binding: FragmentNewQuestionBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: QuestionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.questionState.observe(viewLifecycleOwner, ::handleQuestionState)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun handleQuestionState(state: QuestionState) {
        when (state) {
            is QuestionState.Error -> {
                showErrorMessage(state.text)
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

    private fun finish() {
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