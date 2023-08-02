package com.example.firstappcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

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