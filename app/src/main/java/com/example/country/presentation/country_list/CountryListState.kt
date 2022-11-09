package com.example.country.presentation.country_list

import com.example.country.domain.model.Country

data class CountryListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val error: String = ""
)
