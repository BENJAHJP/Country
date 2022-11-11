package com.example.country.presentation.country_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.country.common.Resource
import com.example.country.domain.model.Country
import com.example.country.domain.use_cases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
): ViewModel() {

    private val _state = mutableStateOf(CountryListState())
    val state: State<CountryListState> = _state

    var open by mutableStateOf(true)
    var timeZoneOpen by mutableStateOf(true)

    var isEnglishEnabled by mutableStateOf(true)
    var isKiswahiliEnabled by mutableStateOf(true)
    var dropdownExpandedState by mutableStateOf(false)

    var englishSelected by mutableStateOf(false)
    var kiswahiliSelected by mutableStateOf(false)

    var africaState by mutableStateOf(false)
    var antarcticaState by mutableStateOf(false)
    var asiaState by mutableStateOf(false)
    var australiaState by mutableStateOf(false)
    var europeState by mutableStateOf(false)
    var northAmericaState by mutableStateOf(false)
    var southAmericaState by mutableStateOf(false)

    var utc01State by mutableStateOf(false)
    var utc02State by mutableStateOf(false)
    var utc03State by mutableStateOf(false)
    var utc04State by mutableStateOf(false)
    var utc05State by mutableStateOf(false)
    var utc06State by mutableStateOf(false)
    var utc07State by mutableStateOf(false)

    var languageSheet by mutableStateOf(false)

    var isFilter by mutableStateOf(false)
        private set

    var query by mutableStateOf("")
        private set

    init {
        getCountries(isSearching = false)
    }

    fun onEvent(countryListEvents: CountryListEvents){
        when(countryListEvents){
            is CountryListEvents.onSearchChanged -> {
                query = countryListEvents.search
            }
        }
    }

    fun getCountries(isSearching:Boolean){
        getCountriesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    if (isSearching){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.name.contains(query.trim(), ignoreCase = true)
                        } ?: emptyList())
                    } else if(africaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("Africa")!!
                        } ?: emptyList())
                    } else if(antarcticaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("Antarctica")!!
                        } ?: emptyList())
                    } else if(asiaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("Asia")!!
                        } ?: emptyList())
                    } else if(australiaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("Australia")!!
                        } ?: emptyList())
                    } else if(europeState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("Europe")!!
                        } ?: emptyList())
                    } else if(northAmericaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("North America")!!
                        } ?: emptyList())
                    }else if(utc01State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O1:00")!!
                        } ?: emptyList())
                    } else if(utc02State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O2:00")!!
                        } ?: emptyList())
                    } else if(utc03State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O3:00")!!
                        } ?: emptyList())
                    } else if(utc04State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O4:00")!!
                        } ?: emptyList())
                    } else if(utc05State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O5:00")!!
                        } ?: emptyList())
                    } else if(utc06State){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.timezones?.get(0)?.contains("UTC+O6:00")!!
                        } ?: emptyList())
                    } else if(southAmericaState){
                        _state.value = CountryListState(countries = result.data?.filter {
                            it.continents?.contains("South America")!!
                        } ?: emptyList())
                    } else {
                        _state.value = CountryListState(countries = result.data?.sortedBy {
                            it.name
                        } ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    _state.value = CountryListState(error = result.message ?: "unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CountryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}