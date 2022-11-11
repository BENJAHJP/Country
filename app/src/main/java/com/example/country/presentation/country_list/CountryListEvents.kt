package com.example.country.presentation.country_list

sealed class CountryListEvents{
    data class onSearchChanged(var search: String): CountryListEvents()
}
