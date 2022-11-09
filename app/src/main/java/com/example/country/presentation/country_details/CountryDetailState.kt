package com.example.country.presentation.country_details

import com.example.country.domain.model.CountryDetails

data class CountryDetailState(
    val isLoading: Boolean = false,
    val countries: CountryDetails? = null,
    val error: String = ""
)