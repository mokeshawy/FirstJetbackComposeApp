package com.example.firstappcompose.gyms.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstappcompose.gyms.gym_details.presentation.GymDetailsScreen
import com.example.firstappcompose.gyms.gyms_list.domain.viewmodel.GymViewModel
import com.example.firstappcompose.gyms.gyms_list.presentation.GymList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAroundApp()
        }
    }
}

@Composable
fun GymAroundApp() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gymListScreen") {
        composable("gymListScreen") {
            val viewModel: GymViewModel = viewModel()
            GymList(state = viewModel.state.value, onFavoriteItemClicked = { id, oldValue ->
                viewModel.handleFavoriteState(id, oldValue)
            }
            ) { gymId ->
                navController.navigate("gyms/$gymId")
            }
        }
        composable(
            route = "gyms/{gym_id}",
            arguments = listOf(navArgument("gym_id") { type = NavType.IntType })
        ) {
            GymDetailsScreen()
        }
    }
}


//Column (Modifier.verticalScroll(rememberScrollState())){
//    listOfGym.forEach {
//        GymItem(it)
//    }
//}