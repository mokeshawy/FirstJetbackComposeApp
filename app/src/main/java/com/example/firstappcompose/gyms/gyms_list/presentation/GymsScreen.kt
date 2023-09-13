package com.example.firstappcompose.gyms.gyms_list.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsScreenState
import com.example.firstappcompose.ui.theme.Purple40


@SuppressLint("MutableCollectionMutableState")
@Composable
fun GymList(
    state: GymsScreenState,
    onFavoriteItemClicked: (Int, oldValue: Boolean) -> Unit,
    onItemCLicked: (Int) -> Unit
) {
    val dataIsLoading = state.isLoading
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(state.gyms) { gym ->
                GymItem(
                    gym,
                    onFavoriteItemClicked = { id, oldValue ->
                        onFavoriteItemClicked(id, oldValue)
                    },
                    onItemCLicked = { onItemCLicked(it) })
            }
        }
        if (dataIsLoading) CircularProgressIndicator()
        state.errorMessage?.let { Text(text = it) }
    }

}


@Composable
fun GymItem(
    gymModel: GymsData,
    onFavoriteItemClicked: (Int, oldValue: Boolean) -> Unit,
    onItemCLicked: (Int) -> Unit
) {
    val icon = if (gymModel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemCLicked(gymModel.id) },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gymModel, Modifier.weight(0.70f))
            DefaultIcon(icon, Modifier.weight(0.15f)) {
                onFavoriteItemClicked(gymModel.id, gymModel.isFavorite)
            }
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

@Composable
fun GymDetails(
    gymModel: GymsData,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(
            text = gymModel.name,
            style = MaterialTheme.typography.titleLarge,
            color = Purple40
        )
        CompositionLocalProvider(LocalContentColor.provides(Color.Gray)) {
            Text(
                text = gymModel.location,
                fontSize = 20.sp,
                maxLines = 4,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


