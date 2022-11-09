package com.example.country.data.dto

import com.example.country.domain.model.Country
import com.example.country.domain.model.CountryDetails

data class CountryDtoItem(
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val capital: List<String>,
    val capitalInfo: CapitalInfo,
    val car: Car,
    val cca2: String,
    val cca3: String,
    val ccn3: String,
    val cioc: String,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val currencies: Currencies,
    val demonyms: Demonyms,
    val fifa: String,
    val flag: String,
    val flags: Flags,
    val gini: Gini,
    val idd: Idd,
    val independent: Boolean,
    val landlocked: Boolean,
    val languages: Languages,
    val latlng: List<Double>,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val postalCode: PostalCode,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String,
    val timezones: List<String>,
    val tld: List<String>,
    val translations: Translations,
    val unMember: Boolean
)

fun CountryDtoItem.toCountry(): Country{
    return Country(
        name = name.common,
        capital = capital,
        flag = flags.png
    )
}

fun CountryDtoItem.toCountryDetails(): CountryDetails{
    return CountryDetails(
        name = name,
        population = population,
        region = region,
        capital = capital,
        officialLanguage = languages,
        area = area,
        fifa = fifa,
        independent = independent,
        landlocked = landlocked,
        postalCode = postalCode,
        status = status,
        subregion = subregion,
        timezones = timezones,
        unMember = unMember,
        startOfWeek = startOfWeek,
        flags = flags
    )
}