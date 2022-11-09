package com.example.country.presentation.country_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.country.common.Resource
import com.example.country.domain.use_cases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    init {

    }

    fun getCountryDetails(){
        getCountriesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {

                }
            }
        }
    }
}