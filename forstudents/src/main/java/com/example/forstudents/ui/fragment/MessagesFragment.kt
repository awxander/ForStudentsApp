package com.example.forstudents.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forstudents.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private lateinit var binding : FragmentMessagesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagesBinding.inflate(inflater)
        return binding.root
    }

}