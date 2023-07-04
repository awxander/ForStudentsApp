package com.example.forstudents.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.forstudents.data.model.IncomingQuestionModel
import com.example.forstudents.databinding.FragmentAnswerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val INCOMING_QUESTION_INFO = "incoming_question_info"

class AnswerFragment() : Fragment() {
    private var username: String? =null
    private var topic: String? = null
    private var question: String? = null

    var incomingQuestionModel : IncomingQuestionModel? = null

    private var _binding : FragmentAnswerBinding ?=  null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
//        arguments?.let {
//            username = it.getString(USER_NAME)
//            topic = it.getString(TOPIC)
//            question = it.getString(QUESTION)
//        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnswerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFields()
        setListeners()
    }

    private fun setListeners(){
        binding.button.setOnClickListener {
            Toast
                .makeText(context, "пока просто смотри, вопрос же красивый", Toast.LENGTH_LONG)
                .show()
        }
    }
    
    private fun setFields(){
        binding.apply { 
            usernameTextView.text = incomingQuestionModel?.username
            topicTextView.text = incomingQuestionModel?.topic
            questionTextView.text = incomingQuestionModel?.body
        }
    }
    

/*    companion object {

        @JvmStatic
        fun newInstance(user: String, topic: String, question: String) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_NAME, user)
                    putString(TOPIC, topic)
                    putString(QUESTION, question)
                }
            }
    }*/
}