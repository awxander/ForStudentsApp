package com.example.forstudents.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forstudents.R
import com.example.forstudents.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {

    private lateinit var binding : FragmentQuestionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater)
        return binding.root
    }

}