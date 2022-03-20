package ru.alexbur.smartwallet.ui.createwallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.createwallet.edittext.OutlinedEditText
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun CreateWalletScreen(
    navigation: NavController,
    viewModel: CreateWalletViewModel = hiltViewModel()
) {
    var nameWallet by remember {
        mutableStateOf("")
    }

    var currencyWallet by remember {
        mutableStateOf("")
    }

    var limitWallet by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.new_wallet),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                ),
                textAlign = TextAlign.Center
            )

            OutlinedEditText(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        navigation.navigate(CreateWalletScreenFactory.route)
                    }
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.title_wallet),
                onValueChanged = { nameWallet = it }
            )

            OutlinedEditText(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.currency),
                isEnabled = false,
                initialField = stringResource(id = Currency.RUB.nameId)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "Arrow right"
                )
            }

            OutlinedEditText(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.limit_wallet),
                onValueChanged = { limitWallet = it },
                initialField = "100000",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLength = 10
            )
        }
    }
}

class CreateWalletScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            CreateWalletScreen(navGraph)
        }
    }

}