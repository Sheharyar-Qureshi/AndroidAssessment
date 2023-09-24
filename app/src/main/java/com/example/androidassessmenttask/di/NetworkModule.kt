package com.example.androidassessmenttask.di

import android.content.Context
import androidx.room.Room
import com.example.androidassessmenttask.constants.Constants
import com.example.androidassessmenttask.core.database.AppDatabase
import com.example.androidassessmenttask.core.network.AndroidAssessmentBaseRepository
import com.example.androidassessmenttask.core.network.AndroidAssessmentBaseRepositoryImpl
import com.example.androidassessmenttask.core.network.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { getGsonConverterFactory() }
    single { getRetrofit(get()) }
    single { getApiService(get()) }
    factory { getRepository(get(), get()) }
    single { getRoomDatabase(androidContext()) }
}

private fun getRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database.db"
    ).build()
}

private fun getGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

private fun getRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

private fun getApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

private fun getRepository(
    apiService: ApiService,
    database: AppDatabase
): AndroidAssessmentBaseRepository {
    return AndroidAssessmentBaseRepositoryImpl(apiService, database)
}