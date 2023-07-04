package com.example.forstudents.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forstudents.databinding.FragmentUserAnswersBinding

class UserAnswersFragment : Fragment() {

    private lateinit var binding : FragmentUserAnswersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserAnswersBinding.inflate(inflater)
        return binding.root
    }

}