package com.tech.mymedicalstoreadminapp.di

import com.tech.mymedicalshopuser.utils.BASE_URL
import com.tech.mymedicalstoreadminapp.data.repository.MedicalRepositoryImpl
import com.tech.mymedicalstoreadminapp.data.services.ApiServices
import com.tech.mymedicalstoreadminapp.domain.repository.MedicalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMedicalApi() :ApiServices{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    @Provides
    @Singleton
    fun provideMedicalRepository(apiServices: ApiServices) : MedicalRepository{
        return MedicalRepositoryImpl(apiServices)
    }

}