package com.example.country.domain.model

import com.example.country.data.dto.Flags
import com.example.country.data.dto.Languages
import com.example.country.data.dto.Name
import com.example.country.data.dto.PostalCode

data class CountryDetails(
    val name: Name,
    val population: Int? = null,
    val region: String? = null,
    val capital: List<String>? = emptyList(),
    val officialLanguage: Languages? = null,
    val area: Double? = null,
    val fifa: String? = null,
    val independent: Boolean? = null,
    val landlocked: Boolean? = null,
    val postalCode: PostalCode? = null,
    val status: String? = null,
    val subregion: String? = null,
    val timezones: List<String>? = emptyList(),
    val unMember: Boolean? = null,
    val startOfWeek: String? = null,
    val flags: Flags? = null
)
