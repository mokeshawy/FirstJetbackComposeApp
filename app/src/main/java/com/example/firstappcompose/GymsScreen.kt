package com.example.firstappcompose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.GymModel
import com.example.firstappcompose.ui.theme.Purple40



@Composable
fun GymList(){
    val viewModel : GymViewModel = viewModel()
    LazyColumn {
        item {
            viewModel.getGymList().forEach {
                GymItem(it) { gymModel ->

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymItem(gymModel: GymModel, onItemCLicked: (GymModel) -> Unit) {
    Card(
        onClick = { onItemCLicked(gymModel) },
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gymModel, Modifier.weight(0.85f))
        }
    }
}

@Composable
fun GymIcon(vector: ImageVector, modifier: Modifier) {
    Image(
        imageVector = vector,
        contentDescription = "",
        modifier = modifier,
        colorFilter = ColorFilter.tint(Color.DarkGray)
    )
}

@Composable
fun GymDetails(gymModel: GymModel, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = gymModel.gymName, style = MaterialTheme.typography.titleLarge, color = Purple40)
        CompositionLocalProvider(LocalContentColor.provides(Color.Gray)) {
            Text(
                text = gymModel.gymDescription,
                fontSize = 20.sp,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
