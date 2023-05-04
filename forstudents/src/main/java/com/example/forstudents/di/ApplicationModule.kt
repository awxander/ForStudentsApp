package com.example.forstudents.di

import com.example.forstudents.data.repository.ForStudentsRepositoryImpl
import com.example.forstudents.domain.repository.ForStudentsRepository
import com.example.forstudents.domain.usecase.AskQuestionUseCase
import com.example.forstudents.domain.usecase.LoadQuestionsUseCase
import com.example.forstudents.domain.usecase.LoginUseCase
import com.example.forstudents.domain.usecase.RegisterUseCase
import com.example.forstudents.presentation.viewmodel.QuestionViewModel
import dagger.Component
import dagger.Module
import dagger.Provides


@Component(modules = [AppModule::class])
interface AppComponent {

    fun questionViewModel() : QuestionViewModel
}


@Module
object AppModule{

    @Provides
    fun provideQuestionViewModel(
        askQuestionUseCase: AskQuestionUseCase,
        loadQuestionsUseCase: LoadQuestionsUseCase
    ) : QuestionViewModel{
        return QuestionViewModel(
            askQuestionUseCase,
            loadQuestionsUseCase
        )
    }

    @Provides
    fun provideAskQuestionUseCase(repository: ForStudentsRepository) : AskQuestionUseCase{
        return AskQuestionUseCase(
            repository
        )
    }

    @Provides
    fun provideLoadQuestionsUseCase(repository: ForStudentsRepository) : LoadQuestionsUseCase{
        return LoadQuestionsUseCase(
            repository
        )
    }

    @Provides
    fun provideLoginUseCase(repository: ForStudentsRepository) : LoginUseCase{
        return LoginUseCase(
            repository
        )
    }

    @Provides
    fun provideRegisterUseCase(repository: ForStudentsRepository) : RegisterUseCase{
        return RegisterUseCase(
            repository
        )
    }

    @Provides
    fun provideForStudentsRepository() : ForStudentsRepository {
        return ForStudentsRepositoryImpl()
    }
}