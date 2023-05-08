package com.example.forstudents.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.forstudents.R
import com.example.forstudents.appComponent
import com.example.forstudents.databinding.FragmentQuestionsBinding
import com.example.forstudents.di.AppComponent
import com.example.forstudents.presentation.LoadQuestionsState
import com.example.forstudents.presentation.viewmodel.QuestionViewModel
import com.example.forstudents.ui.adapter.IncomingQuestionsAdapter
import com.example.forstudents.util.hideBottomNavigation
import com.example.forstudents.util.showBottomNavigation
import javax.inject.Inject

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel : QuestionViewModel
    private val adapter  =IncomingQuestionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation()
        setListeners()
        setObservers()
        initRecyclerView()
        viewModel.loadQuestions()
    }

    private fun initRecyclerView(){
        binding.recyclerView.adapter = adapter
    }

    private fun setObservers(){
        viewModel.loadQuestionsState.observe(viewLifecycleOwner, ::handleReceivedQuestionsState)
    }

    private fun handleReceivedQuestionsState(loadQuestionsState: LoadQuestionsState){
        when(loadQuestionsState){
            is LoadQuestionsState.Content -> adapter.insertQuestions(loadQuestionsState.questions)
            is LoadQuestionsState.Error -> showErrorMsg(loadQuestionsState.text)
            LoadQuestionsState.Initial -> Unit
            LoadQuestionsState.Loading -> Unit//TODO добавить progressbar
        }
    }

    private fun showErrorMsg(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
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
        _binding = FragmentQuestionsBinding.inflate(inflater)
        return binding.root
    }


}