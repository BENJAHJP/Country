package com.example.country.domain.model

import com.example.country.data.dto.Flags
import com.example.country.data.dto.Name

data class Country(
    val name: String,
    val capital: List<String?>? = null,
    val flag: String
)
