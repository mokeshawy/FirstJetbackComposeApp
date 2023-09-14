package com.example.firstappcompose.gyms.gym_details.presentation

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
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import com.example.firstappcompose.gyms.gyms_list.presentation.DefaultIcon
import com.example.firstappcompose.gyms.gyms_list.presentation.GymDetails


@Composable
fun GymDetailsScreen(item : GymsData?) {

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