package com.example.country.domain.use_cases

import com.example.country.common.Resource
import com.example.country.data.CountryApi
import com.example.country.data.dto.Name
import com.example.country.data.dto.toCountry
import com.example.country.data.dto.toCountryDetails
import com.example.country.domain.model.Country
import com.example.country.domain.model.CountryDetails
import com.example.country.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.threadName
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(
) {

}