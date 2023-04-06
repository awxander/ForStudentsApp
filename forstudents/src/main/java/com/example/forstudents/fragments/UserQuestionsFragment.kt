package com.example.forstudents.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forstudents.R
import com.example.forstudents.databinding.FragmentMessagesBinding
import com.example.forstudents.databinding.FragmentUserQuestionsBinding

class UserQuestionsFragment : Fragment() {

    private lateinit var binding : FragmentUserQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserQuestionsBinding.inflate(inflater)
        return binding.root
    }
}