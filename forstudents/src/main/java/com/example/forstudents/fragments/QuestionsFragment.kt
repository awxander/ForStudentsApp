package com.example.forstudents.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forstudents.MainActivity
import com.example.forstudents.R
import com.example.forstudents.databinding.FragmentQuestionsBinding
import com.example.forstudents.util.hideBottomNavigation
import com.example.forstudents.util.showBottomNavigation

class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            .replace(R.id.fragmentContainerView, NewQuestionFragment())
            .addToBackStack(null)
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