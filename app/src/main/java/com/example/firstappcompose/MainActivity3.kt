package com.example.firstappcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity3 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setComposableMethods()
    }


    private fun ComponentActivity.setComposableMethods() =
        setContent {
            BoxWidget{
                Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BoxWidget(onItemCLicked : () -> Unit) {
        Card(onClick = onItemCLicked ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            RoWidget()
        }
    }

    @Composable
    fun RoWidget() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageWidget()
            Spacer(modifier = Modifier.width(10.dp))
            ColumnWidget()
        }
    }


    @Composable
    fun ImageWidget() {
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_icon),
            contentDescription = "",
            modifier = Modifier.width(100.dp)
        )
    }

    @Composable
    fun ColumnWidget() {
        Column {
            Text(
                text = "The standard Lorem Ipsum passage",
                style = TextStyle(fontSize = 17.sp, fontFamily = FontFamily.Default)
            )
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ")
        }
    }
}