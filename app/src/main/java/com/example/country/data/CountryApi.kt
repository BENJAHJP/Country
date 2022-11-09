package com.example.country.data

import com.example.country.data.dto.CountryDtoItem
import com.example.country.domain.model.CountryDetails
import retrofit2.http.GET

interface CountryApi {
    @GET("/v3.1/all")
    suspend fun getCountries(): List<CountryDtoItem>

}