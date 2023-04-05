package com.example.forstudents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.forstudents.R
import com.example.forstudents.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private lateinit var binding : FragmentMessagesBinding
    private val actionBar  = (activity as AppCompatActivity).supportActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagesBinding.inflate(inflater)
        return binding.root
    }



}