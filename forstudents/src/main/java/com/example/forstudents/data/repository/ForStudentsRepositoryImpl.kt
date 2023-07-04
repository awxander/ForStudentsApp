package com.example.forstudents.data.repository

import com.example.forstudents.data.model.QuestionModel
import com.example.forstudents.data.model.UserLoginModel
import com.example.forstudents.data.model.UserRegisterModel
import com.example.forstudents.data.api.ForStudentsApi
import com.example.forstudents.data.model.IncomingQuestionModel
import com.example.forstudents.domain.repository.ForStudentsRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ForStudentsRepositoryImpl : ForStudentsRepository {
    companion object{
        const val BASE_URL ="http://192.168.8.100:8080/"
//        const val BASE_URL ="http://10.9.47.163:8080/"
        const val READ_TIMEOUT_SECONDS = 5L
        const val CONNECT_TIMEOUT_SECONDS = 5L
        const val WRITE_TIMEOUT_SECONDS = 5L
    }


    private val gson = GsonBuilder()
        .create()


    private val forStudentsApi by lazy{
        retrofit.create(ForStudentsApi::class.java)
    }

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    private fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()


    override suspend fun loginUser(userLoginModel: UserLoginModel) = forStudentsApi.login(userLoginModel)

    override suspend fun registerUser(userRegisterModel: UserRegisterModel) = forStudentsApi.register(userRegisterModel)

    override suspend fun askQuestion(questionModel: QuestionModel) = forStudentsApi.askQuestion(questionModel)

    override suspend fun loadQuestions() : List<IncomingQuestionModel> = forStudentsApi.loadQuestions()
}