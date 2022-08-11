package com.example.lemonadecompose

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonadecompose.ui.theme.LemonadeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeMain()
                }
            }
        }
    }
}

@Composable
fun LemonadeImageText() {
    var result by remember {
        mutableStateOf(5)
    }
    var checkFlag = false
    var step by remember {
        mutableStateOf(1)
    }

    var lemonLabel = when (step) {
        1 -> R.string.Step1
        2 -> R.string.Step2
        3 -> R.string.Step3
        else -> R.string.Step4
    }
    var lemonImage = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.clickable {
                if (step == 2) {
                    if ((result == 5) && !checkFlag) {
                        result = (2..4).random()
                        checkFlag = true
                    } else {
                        result -= 1
                        if (result == 0) {
                            step = 3
                            result = 5
                            checkFlag = false
                        }
                    }
                }
                else if (step < 4) {
                    step += 1
                } else step = 1
                 },
            painter = painterResource(id = lemonImage),
            contentDescription = stringResource(id = R.string.LemonTree))
        Text(text = stringResource(id = lemonLabel))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeMain() {
    LemonadeComposeTheme {
        LemonadeImageText()
    }
}