package ru.alexbur.smartwallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.theme.SmartWalletTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SmartWallet)
        setContent {
            SmartWalletTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AuthorizationScreenFactory.route
                ) {
                    factorySet.forEach { it.create(this, navController) }
                }
            }
        }
    }
}