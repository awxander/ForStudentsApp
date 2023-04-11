package com.example.forstudents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forstudents.databinding.FragmentNewQuestionBinding

class NewQuestionFragment : Fragment() {


    private lateinit var binding: FragmentNewQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentNewQuestionBinding.inflate(inflater)
        return binding.root
    }


}