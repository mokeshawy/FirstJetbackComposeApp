package com.example.firstappcompose.gym_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firstappcompose.gym_activity.main_screen.presentation.screen.GymList

class MainActivity4 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymList()
//            Column (Modifier.verticalScroll(rememberScrollState())){
//                listOfGym.forEach {
//                    GymItem(it)
//                }
//            }
        }
    }
}