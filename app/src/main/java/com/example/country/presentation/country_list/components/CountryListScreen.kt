package com.example.country.presentation.country_list.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.country.presentation.country_list.CountryListEvents
import com.example.country.presentation.country_list.CountryListViewModel
import com.example.country.presentation.navigation.Screen
import com.example.country.presentation.theme.Purple80
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountryListScreen(
    navHostController: NavHostController,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetElevation = 5.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.primary,
        backgroundColor = MaterialTheme.colorScheme.primary,
        sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(150.dp)
                ,
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row() {
                        Text(
                            text = "Explore",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Auto Dark Mode",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search, contentDescription = "search",
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        },
                        value = viewModel.query,
                        onValueChange = {
                            viewModel.onEvent(CountryListEvents.onSearchChanged(it))
                        },
                        placeholder = {
                            Text(text = "Search Country",
                                color = MaterialTheme.colorScheme.tertiary
                            )

                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(onSearch = {
                            viewModel.getCountries(isSearching = true)
                        })
                    )

                    Row() {
                        OutlinedButton(onClick = {
                            viewModel.languageSheet = true
                            scope.launch {
                                sheetState.expand()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Language, contentDescription = "language")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = {
                            viewModel.languageSheet = false
                            scope.launch {
                                sheetState.expand()
                            }
                        }) {
                            Text(text = "Filter")
                        }
                    }
                }
            }
        },
        sheetContent = {
            if (viewModel.languageSheet){
                Column(
                    modifier = Modifier
                        .height(450.dp)
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Language",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                scope.launch {
                                    sheetState.collapse()
                                }
                            },
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "cancel"
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Auto Language supported Both English And Kiswahili",
                        color = MaterialTheme.colorScheme.tertiary
                        )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "English",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        RadioButton(
                            selected = viewModel.englishSelected,
                            onClick = {
                                viewModel.englishSelected = true
                                viewModel.isKiswahiliEnabled = false
                        },
                            enabled = viewModel.isEnglishEnabled
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Kiswahili",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        RadioButton(selected = viewModel.kiswahiliSelected,
                            onClick = {
                                viewModel.kiswahiliSelected = true
                                viewModel.isEnglishEnabled = false
                            },
                            enabled = viewModel.isKiswahiliEnabled
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .height(450.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Filter",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                scope.launch {
                                    sheetState.collapse()
                                }
                            },
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "cancel"
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "continent",
                            color = MaterialTheme.colorScheme.tertiary
                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = if (!viewModel.open){
                                Modifier.clickable {
                                    viewModel.open = true
                                }
                            }else {
                                Modifier.clickable {
                                    viewModel.open = false
                                } },
                            imageVector = if (!viewModel.open){
                                Icons.Default.ArrowDropDown
                            }else {
                                Icons.Default.ArrowDropUp
                            },
                            contentDescription = if (!viewModel.open){
                                "dropup"
                            }else {
                                "dropdown"
                            }
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "TimeZone",
                            color = MaterialTheme.colorScheme.tertiary
                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = if (!viewModel.timeZoneOpen){
                                Modifier.clickable {
                                    viewModel.timeZoneOpen = true
                                }
                            }else {
                                Modifier.clickable {
                                    viewModel.timeZoneOpen = false
                                } },
                            imageVector = if (!viewModel.timeZoneOpen){
                                Icons.Default.ArrowDropDown
                            }else {
                                Icons.Default.ArrowDropUp
                            },
                            contentDescription = if (!viewModel.timeZoneOpen){
                                "dropUp"
                            }else {
                                "dropdown"
                            }
                        )
                    }

                    Column(
                        modifier = if (viewModel.timeZoneOpen) {
                            Modifier.height(450.dp)
                        } else {
                            Modifier.height(0.dp)
                        }
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+01:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc01State,
                                onCheckedChange = { viewModel.utc01State = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+02:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc02State,
                                onCheckedChange = { viewModel.utc02State = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+03:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc03State,
                                onCheckedChange = { viewModel.utc03State = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+04:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary)
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc04State,
                                onCheckedChange = { viewModel.utc04State = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+05:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc05State,
                                onCheckedChange = { viewModel.utc05State = it }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+06:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc06State,
                                onCheckedChange = { viewModel.utc06State = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "UTC+07:00",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.utc07State,
                                onCheckedChange = { viewModel.utc07State = it }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            OutlinedButton(onClick = {
                                viewModel.utc01State = false
                                viewModel.utc02State = false
                                viewModel.utc03State = false
                                viewModel.utc04State = false
                                viewModel.utc05State = false
                                viewModel.utc06State = false
                                viewModel.utc07State = false
                            }) {
                                Text(text = "Reset")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = {
                                scope.launch {
                                    sheetState.collapse()
                                }
                                viewModel.getCountries(isSearching = false)
                            }) {
                                Text(text = "Show Results") }
                        }
                    }

                    Column(
                        modifier = if (viewModel.open){
                            Modifier.height(450.dp)
                        } else {
                            Modifier.height(0.dp)
                        }.verticalScroll(rememberScrollState())
                    ) { 
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically, 
                            horizontalArrangement = Arrangement.Center
                        ) { 
                            Text(
                                text = "Africa",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.africaState,
                                onCheckedChange = { viewModel.africaState = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Antarctica",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.antarcticaState,
                                onCheckedChange = { viewModel.antarcticaState = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Asia",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.asiaState,
                                onCheckedChange = { viewModel.asiaState = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Australia",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.australiaState,
                                onCheckedChange = { viewModel.australiaState = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Europe",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.europeState,
                                onCheckedChange = { viewModel.europeState = it }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "North America",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.northAmericaState,
                                onCheckedChange = { viewModel.northAmericaState = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "South America",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = viewModel.southAmericaState,
                                onCheckedChange = { viewModel.southAmericaState = it }
                            )
                        }
                    
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            OutlinedButton(onClick = {
                                viewModel.africaState = false
                                viewModel.antarcticaState = false
                                viewModel.asiaState = false
                                viewModel.australiaState = false
                                viewModel.europeState = false
                                viewModel.northAmericaState = false
                                viewModel.southAmericaState = false
                            }) {
                                Text(text = "Reset")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = {
                                scope.launch {
                                    sheetState.collapse()
                                }
                                viewModel.getCountries(isSearching = false)
                            }) {
                                Text(text = "Show Results") }
                        }
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(5.dp)
            ) {
                items(state.countries) { country ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    val details = country
                                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "details",
                                        value = country
                                    )
                                    navHostController.navigate(Screen.CountryDetailScreen.route)
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberImagePainter(country.flag),
                                contentDescription = "image",
                                modifier = Modifier
                                    .width(50.dp)
                                    .padding(2.dp)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                            )

                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = country.name,
                                    fontSize = 25.sp,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                country.capital?.get(0)?.let { Text(text = it,
                                    color = MaterialTheme.colorScheme.tertiary) }
                            }
                        }
                        Divider()
                    }
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = androidx.compose.material.MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}