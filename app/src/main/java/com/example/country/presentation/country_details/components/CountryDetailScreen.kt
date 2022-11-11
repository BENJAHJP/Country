package com.example.country.presentation.country_details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.country.R
import com.example.country.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    country: Country,
    navHostController: NavHostController
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        modifier = Modifier.clickable {
                            navHostController.popBackStack()
                        }
                            .padding(5.dp)
                            .clip(CircleShape)
                        ,
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.weight(0.2f))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = country.name,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            Image(
                painter = rememberImagePainter(country.flag),
                contentDescription = "image",
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            )
            Row {
                Text(
                    text = stringResource(id = R.string.population),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = country.population.toString(),
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Row {
                Text(text = stringResource(id = R.string.region),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(text = country.region.toString(),
                    textAlign = TextAlign.Left,
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Row {
                Text(text = stringResource(id = R.string.capital),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                    )
                country.capital?.get(0)?.let { it1 -> Text(text = it1,
                    color = MaterialTheme.colorScheme.tertiary) }
            }

            Row {
                Text(text = stringResource(id = R.string.independent),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.independent.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(text = stringResource(id = R.string.area),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.area.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Row {
                Text(text = stringResource(id = R.string.fifa),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.fifa.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Row {
                Text(text = stringResource(id = R.string.landlocked),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.landlocked.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Row {
                Text(text = stringResource(id = R.string.status),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.status.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(text = stringResource(id = R.string.sub_region),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                country.subregion?.let { it1 -> Text(text = it1,
                    color = MaterialTheme.colorScheme.tertiary) }
            }

            Row {
                Text(text = stringResource(id = R.string.un_member),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                Text(text = country.unMember.toString(),
                    color = MaterialTheme.colorScheme.tertiary)
            }

            Row {
                Text(text = stringResource(id = R.string.start_of_week),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                country.startOfWeek?.let { it1 -> Text(text = it1,
                    color = MaterialTheme.colorScheme.tertiary) }
            }

            Row {
                Text(text = stringResource(id = R.string.time_zone),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                country.timezones?.get(0)?.let { it1 -> Text(text = it1,
                    color = MaterialTheme.colorScheme.tertiary) }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(text = stringResource(id = R.string.continent),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary)
                country.continents?.get(0)?.let { it1 -> Text(text = it1,
                    color = MaterialTheme.colorScheme.tertiary) }
            }
        }
    }
}