package ru.alexbur.koshelok.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.alexbur.koshelok.ui.auth.AuthorizationScreen
import ru.alexbur.koshelok.ui.theme.KoshelOkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoshelOkTheme {
                // A surface container using the 'background' color from the theme
                AuthorizationScreen(navController = null)
            }
        }
    }
}