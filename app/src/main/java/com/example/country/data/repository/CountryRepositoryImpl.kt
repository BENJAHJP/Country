package com.example.country.data.repository

import com.example.country.data.CountryApi
import com.example.country.data.dto.CountryDtoItem
import com.example.country.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi
): CountryRepository {
    override suspend fun getCountries(): List<CountryDtoItem> {
        return countryApi.getCountries()
    }
}