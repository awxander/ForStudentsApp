package com.example.forstudents.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forstudents.R
import com.example.forstudents.data.model.IncomingQuestionModel
import com.example.forstudents.databinding.QuestionItemBinding

class IncomingQuestionsAdapter(private val onClick: (IncomingQuestionModel) -> Unit) :
    RecyclerView.Adapter<IncomingQuestionsAdapter.IncomingQuestionHolder>() {


    private val incomingQuestions = ArrayList<IncomingQuestionModel>()

    class IncomingQuestionHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val itemBinding = QuestionItemBinding.bind(view)

        fun bind(incomingQuestion: IncomingQuestionModel, onClick: (IncomingQuestionModel) -> Unit) {
            itemBinding.apply {
                tvTopic.text = incomingQuestion.topic
                tvQuestion.text = incomingQuestion.body
                itemView.setOnClickListener {
                    onClick(incomingQuestion)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomingQuestionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item, parent, false)
        return IncomingQuestionHolder(view)
    }

    override fun getItemCount(): Int {
        return incomingQuestions.size
    }

    override fun onBindViewHolder(holder: IncomingQuestionHolder, position: Int) {
        holder.bind(incomingQuestions[position], onClick)
    }

    fun insertQuestions(questions: List<IncomingQuestionModel>) {
        incomingQuestions.clear()
        incomingQuestions.addAll(questions)
        notifyDataSetChanged()
    }
}