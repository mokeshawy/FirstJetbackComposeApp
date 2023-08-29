package com.example.firstappcompose.gym_activity.gym_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstappcompose.gym_activity.gym_details.domain.viewmodel.GymDetailsViewModel
import com.example.firstappcompose.gym_activity.main_screen.presentation.screen.DefaultIcon
import com.example.firstappcompose.gym_activity.main_screen.presentation.screen.GymDetails


@Composable
fun GymDetailsScreen() {

    val viewModel: GymDetailsViewModel = viewModel()
    val item = viewModel.state

    item?.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            DefaultIcon(
                icon = Icons.Filled.Place,
                modifier = Modifier.padding(bottom = 32.dp, top = 32.dp)
            )
            GymDetails(
                gymModel = it,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )

            Text(
                text = if (it.gymStatus) "Gym is Open" else "Gym is Closed",
                color = if (it.gymStatus) Color.Green else Color.Red
            )
        }
    }
}