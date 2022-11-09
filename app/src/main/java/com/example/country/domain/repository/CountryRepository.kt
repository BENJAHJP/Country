package com.example.country.domain.repository

import com.example.country.data.dto.CountryDtoItem

interface CountryRepository {
    suspend fun getCountries(): List<CountryDtoItem>
}