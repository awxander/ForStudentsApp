package com.example.forstudents.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.forstudents.R
import com.example.forstudents.appComponent
import com.example.forstudents.data.model.IncomingQuestionModel
import com.example.forstudents.databinding.FragmentQuestionsBinding
import com.example.forstudents.presentation.state.LoadQuestionsState
import com.example.forstudents.presentation.viewmodel.QuestionViewModel
import com.example.forstudents.ui.adapter.IncomingQuestionsAdapter
import com.example.forstudents.util.hideBottomNavigation
import com.example.forstudents.util.showBottomNavigation
import javax.inject.Inject

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: QuestionViewModel
    private val adapter = IncomingQuestionsAdapter(::startAnswerFragment)


    companion object {
        const val PROGRESSBAR_AXES_AMOUNT = 3
    }

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

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun setObservers() {
        viewModel.loadQuestionsState.observe(viewLifecycleOwner, ::handleReceivedQuestionsState)
    }

    private fun handleReceivedQuestionsState(loadQuestionsState: LoadQuestionsState) {
        when (loadQuestionsState) {
            is LoadQuestionsState.Content -> {
                binding.apply {
                    errorMsgTextView.isVisible = false
                    progressBar.stopNestedScroll()
                    progressBar.isVisible = false
                    recyclerView.isVisible = true
                }
                adapter.insertQuestions(loadQuestionsState.questions)
            }

            is LoadQuestionsState.Error -> {
                binding.recyclerView.isVisible = false
                binding.progressBar.stopNestedScroll()
                binding.progressBar.isVisible = false
                showErrorMsg(loadQuestionsState.text)
            }

            LoadQuestionsState.Initial -> {
                Unit
            }

            LoadQuestionsState.Loading -> {
                binding.recyclerView.isVisible = false
                showProgressBar()
            }
        }
    }


    private fun showProgressBar() {
        binding.progressBar.apply {
            isVisible = true
            startNestedScroll(PROGRESSBAR_AXES_AMOUNT)
        }
    }

    private fun showErrorMsg(msg: String) {
        binding.errorMsgTextView.apply {
            isVisible = true
            text = msg
        }
    }

    private fun setListeners() {
        binding.createQuestionButton.setOnClickListener {
            hideBottomNavigation()
            startNewQuestionFragment()
        }
    }


    private fun startNewQuestionFragment() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, NewQuestionFragment())
            .commit()
    }

    private fun startAnswerFragment(incomingQuestionModel: IncomingQuestionModel){
        hideBottomNavigation()
        val answerFragment = AnswerFragment()
        answerFragment.incomingQuestionModel = incomingQuestionModel.copy()

        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, answerFragment)
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