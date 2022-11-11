package com.example.country.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.country.domain.model.Country
import com.example.country.presentation.country_details.components.CountryDetailScreen
import com.example.country.presentation.country_list.components.CountryListScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CountryListScreen.route
    ){
        composable(route = Screen.CountryListScreen.route){
            CountryListScreen(navHostController = navHostController)
        }
        composable(route = Screen.CountryDetailScreen.route){
            val country = navHostController.previousBackStackEntry?.savedStateHandle?.get<Country>("details")
            if (country != null) {
                CountryDetailScreen(country, navHostController)
            }
        }
    }
}