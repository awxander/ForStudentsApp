package com.example.forstudents.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.forstudents.R
import com.example.forstudents.data.repository.ForStudentsRepositoryImpl
import com.example.forstudents.databinding.FragmentQuestionsBinding
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import com.example.forstudents.domain.usecase.LoadQuestionsUseCase
import com.example.forstudents.presentation.viewmodel.QuestionViewModel
import com.example.forstudents.util.hideBottomNavigation
import com.example.forstudents.util.showBottomNavigation

class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private val viewModel = QuestionViewModel(
        AskQuestionUseCase(ForStudentsRepositoryImpl()),
        LoadQuestionsUseCase(ForStudentsRepositoryImpl()),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation()
        setListeners()

    }

    private fun setListeners(){
        binding.createQuestionButton.setOnClickListener {
            startNewQuestionFragment()
            hideBottomNavigation()
        }
    }


    private fun startNewQuestionFragment(){
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, NewQuestionFragment())
            .commit()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater)
        return binding.root
    }


}