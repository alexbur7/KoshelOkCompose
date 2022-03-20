package ru.alexbur.smartwallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.AndroidEntryPoint
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.listwallet.MainScreenFactory
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.theme.SmartWalletTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        setTheme(R.style.Theme_SmartWallet)
        setContent {
            SmartWalletTheme {
                val navController = rememberNavController()
                Surface() {
                    NavHost(
                        navController = navController,
                        startDestination = if (account == null) AuthorizationScreenFactory.route else MainScreenFactory.route
                    ) {
                        factorySet.forEach {
                            it.create(this, navController)
                        }
                    }
                }
            }
        }
    }
}