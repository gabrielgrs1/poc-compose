package com.gabrielgrs1.poccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.gabrielgrs1.poccompose.sampledata.sampleSections
import com.gabrielgrs1.poccompose.ui.screens.HomeScreen
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    POCComposeTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}