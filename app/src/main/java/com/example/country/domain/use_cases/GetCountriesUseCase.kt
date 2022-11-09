package com.example.country.domain.use_cases

import com.example.country.common.Resource
import com.example.country.data.dto.toCountry
import com.example.country.domain.model.Country
import com.example.country.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val country = countryRepository.getCountries().map { it.toCountry() }
            emit(Resource.Success(country))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?:"An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("can't reach server, check your internet connection"))
        }
    }
}