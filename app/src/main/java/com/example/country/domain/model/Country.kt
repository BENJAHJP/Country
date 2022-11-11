package com.example.country.domain.model

import android.os.Parcelable
import com.example.country.data.dto.Languages
import com.example.country.data.dto.PostalCode
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Country(
    val name: String,
    val capital: List<String?>? = null,
    val flag: String,
    val population: Int?,
    val region: String?,
    val area: Double?,
    val fifa: String?,
    val independent: Boolean?,
    val landlocked: Boolean?,
    val status: String?,
    val subregion: String?,
    val unMember: Boolean?,
    val startOfWeek: String?,
    val officialLanguage: @RawValue Languages? = null,
    val timezones: List<String?>? = null,
    val postalCode: @RawValue PostalCode? = null,
    val continents: List<String?>? = null
) : Parcelable
