package com.example.firstappcompose

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstappcompose.ui.theme.Purple40


@Composable
fun ScoreSection() {
    var score by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = TextStyle(fontSize = 30.sp),
            color = colorResource(id = R.color.black),
            text = "Source: $score"
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            onClick = { score += 1 }
        ) {
            Text(text = "Add Points")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            enabled = score > 0,
            onClick = { if (score > 0) score -= 1 }
        ) {
            Text(text = "Minus Points")
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

fun ComponentActivity.setComposableMethods() =
    setContent {
        BoxWidget{
            Toast.makeText(this,"Hello", Toast.LENGTH_SHORT).show()
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