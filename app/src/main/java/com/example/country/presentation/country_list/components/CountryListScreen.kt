package com.example.country.presentation.country_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.country.presentation.country_list.CountryListViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CountryListScreen(
    navHostController: NavHostController,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(5.dp)
        ){
            items(state.countries){ country->
                Column {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {

                        Image(
                            painter = rememberImagePainter(
                                data = country.flag,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "image")
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(
                                text = country.name,
                                fontSize = 30.sp,
                                color = Color.Black
                            )
                            country.capital?.get(0)?.let { Text(text = it) }
                        }
                    }
                }
            }
        }

        if (state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading){
            CircularProgressIndicator()
        }
    }
}