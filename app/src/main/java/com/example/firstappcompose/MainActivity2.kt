package com.example.firstappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstappcompose.ui.theme.Purple40

class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                MyText()
                MyButton()
                MyTextField()
                MyImage()
            }
        }
    }
}


@Composable
fun MyText() {
    // Text have a 'text' mandatory argument only
    Text(
        text = "Elephants ca sense storms",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        style = TextStyle(
            color = Purple40,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ),
        maxLines = 2
    )
}

@Composable
fun MyButton() {
    // onClick is mandatory argument in Button
    var buttonIsEnabled by remember { mutableStateOf(true) }
    Button(
        onClick = { buttonIsEnabled = false },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        enabled = buttonIsEnabled
    ) {
        Text(text = if (buttonIsEnabled) "Click Me" else "I'm disabled")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField() {
    // value and onValueChange is mandatory argument in TextField
    var emailAddress by remember { mutableStateOf("") }
    TextField(
        value = emailAddress, onValueChange = {
            emailAddress = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        label = {
            Text(text = "Email Address")
        }
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.jetpack_compose_icon),
        contentDescription = ""
    )
}