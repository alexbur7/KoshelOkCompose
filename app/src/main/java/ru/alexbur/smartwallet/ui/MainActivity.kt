package ru.alexbur.smartwallet.ui

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.data.extentions.filter
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationHostFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.navbar.NavItem
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesViewModel
import ru.alexbur.smartwallet.ui.utils.theme.SmartWalletTheme
import ru.alexbur.smartwallet.ui.wallet.detail.DetailWalletViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationScreenFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>

    @Inject
    lateinit var navigationHostFactorySet: @JvmSuppressWildcards Set<NavigationHostFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        setTheme(R.style.Theme_SmartWallet)
        setContent {
            SmartWalletTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = if (account == null) NavItem.Authorization.route else NavItem.MainScreen.route
                    ) {
                        mutableSetOf<NavigationFactory>().apply {
                            addAll(
                                navigationScreenFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                            addAll(
                                navigationHostFactorySet
                                    .filter(NavigationFactory.NavigationFactoryType.Main)
                            )
                        }.forEach {
                            it.create(this, navController)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val inputMethodManager= getSystemService(
            INPUT_METHOD_SERVICE
        ) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun noteDetailViewModelFactory(): DetailWalletViewModel.Factory

        fun categoriesViewModelFactory(): CategoriesViewModel.Factory
    }
}