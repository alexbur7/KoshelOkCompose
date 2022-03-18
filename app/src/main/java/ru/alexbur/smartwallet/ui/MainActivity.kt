package ru.alexbur.smartwallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreen
import ru.alexbur.smartwallet.ui.theme.SmartWalletTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartWalletTheme {
                // A surface container using the 'background' color from the theme
                AuthorizationScreen(navController = null)
            }
        }
    }
}