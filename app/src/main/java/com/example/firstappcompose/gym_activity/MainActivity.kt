package com.example.firstappcompose.gym_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firstappcompose.gym_activity.gym_details.presentation.GymDetailsScreen
import com.example.firstappcompose.gym_activity.main_screen.presentation.screen.GymList

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
            GymList() { gymId ->
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