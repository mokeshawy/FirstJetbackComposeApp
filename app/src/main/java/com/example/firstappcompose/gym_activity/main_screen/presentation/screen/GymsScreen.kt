package com.example.firstappcompose.gym_activity.main_screen.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import com.example.firstappcompose.gym_activity.main_screen.domain.viewmodel.GymViewModel
import com.example.firstappcompose.ui.theme.Purple40


@SuppressLint("MutableCollectionMutableState")
@Composable
fun GymList() {
    val viewModel: GymViewModel = viewModel()
    LazyColumn {
        items(viewModel.state) { gym ->
            GymItem(gym) { id ->
                viewModel.handleFavoriteState(id)
            }
        }
    }
}


@Composable
fun GymItem(gymModel: GymsResponseDto, onItemCLicked: (Int) -> Unit) {
    val icon = if (gymModel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gymModel, Modifier.weight(0.70f))
            DefaultIcon(icon, Modifier.weight(0.15f)) {
                onItemCLicked(gymModel.id)
            }
        }
    }
}

@Composable
fun GymDetails(gymModel: GymsResponseDto, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = gymModel.gym_name,
            style = MaterialTheme.typography.titleLarge,
            color = Purple40
        )
        CompositionLocalProvider(LocalContentColor.provides(Color.Gray)) {
            Text(
                text = gymModel.gym_location,
                fontSize = 20.sp,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun DefaultIcon(icon: ImageVector, modifier: Modifier, onItemCLicked: () -> Unit = {}) {
    Image(
        imageVector = icon,
        contentDescription = "",
        modifier = modifier
            .padding(10.dp)
            .clickable { onItemCLicked() },
        colorFilter = ColorFilter.tint(Color.DarkGray)
    )
}
