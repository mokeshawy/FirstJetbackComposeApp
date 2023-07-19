package com.example.firstappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var score by remember { mutableStateOf(0) }
            ScoreSection(
                score = score,
                countCallBack = { score += 1 },
                minusCountCallBack = { if (score > 0) score -= 1 })
        }
    }
}

@Composable
fun ScoreSection(score: Int, countCallBack: () -> Unit, minusCountCallBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            color = colorResource(id = R.color.teal_700),
            text = "Source: $score"
        )
        Button(onClick = { countCallBack() }) {
            Text(text = "Add Points")
        }

        Button(onClick = { minusCountCallBack() }) {
            Text(text = "Minus Points")
        }
    }
}

