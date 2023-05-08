package com.example.forstudents.di

import com.example.forstudents.MainActivity
import com.example.forstudents.data.repository.ForStudentsRepositoryImpl
import com.example.forstudents.domain.repository.ForStudentsRepository
import com.example.forstudents.ui.fragments.NewQuestionFragment
import com.example.forstudents.ui.fragments.QuestionsFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Singleton
@Component(modules = [DomainModule::class])
interface AppComponent {
    fun inject(activity : MainActivity)

    fun inject(questionsFragment: QuestionsFragment)

    fun inject(newQuestionFragment: NewQuestionFragment)
}


