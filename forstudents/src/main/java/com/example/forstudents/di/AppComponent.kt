package com.example.forstudents.di

import com.example.forstudents.ui.activity.MainActivity
import com.example.forstudents.ui.fragment.NewQuestionFragment
import com.example.forstudents.ui.fragment.QuestionsFragment
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [DomainModule::class])
interface AppComponent {
    fun inject(activity : MainActivity)

    fun inject(questionsFragment: QuestionsFragment)

    fun inject(newQuestionFragment: NewQuestionFragment)
}


