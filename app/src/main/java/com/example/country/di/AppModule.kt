package com.example.country.di

import com.example.country.common.Constants
import com.example.country.data.CountryApi
import com.example.country.data.repository.CountryRepositoryImpl
import com.example.country.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCountryApi(): CountryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(countryApi: CountryApi): CountryRepository {
        return CountryRepositoryImpl(countryApi)
    }
}