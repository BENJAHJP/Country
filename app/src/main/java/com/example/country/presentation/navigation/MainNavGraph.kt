package com.example.country.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    }
}