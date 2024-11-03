package com.example.lemonadeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeproject.ui.theme.LemonadeProjectTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeProjectTheme() {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreviewApp(){

    LemonadeApp(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier){

    var result by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    if (result == 2 && squeezeCount == 0) {
        squeezeCount = Random.nextInt(2, 5)
    }

    val (imageResource, description) = when (result) {
        1 -> R.drawable.lemon_tree to R.string.tree
        2 -> R.drawable.lemon_squeeze to R.string.lemon
        3 -> R.drawable.lemon_drink to R.string.lemonade
        else -> R.drawable.lemon_restart to R.string.empty_glass
    }


    Column(modifier = Modifier
        .background(colorResource(R.color.title))
        .fillMaxWidth()
        .size(110.dp)
        .wrapContentSize(Alignment.BottomCenter)) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )
    }

        Column(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally){

        Button(onClick = { if (result == 2) {

            if (squeezeCount > 0) {
                squeezeCount--
            }
            if (squeezeCount == 0) {
                result = 3
            }
        } else {

            result = if (result < 4) result + 1 else 1

        }},
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.limonade_bg)),
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier) {

            Image(painter = painterResource(imageResource), contentDescription = String())
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(description),
            fontSize = 20.sp)
    }
}


