package ru.alexbur.smartwallet.ui.wallet.createwallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.utils.OutlinedButton
import ru.alexbur.smartwallet.ui.utils.OutlinedEditText
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency.CurrenciesScreenFactory
import javax.inject.Inject

@Composable
fun CreateWalletScreen(
    navigation: NavController,
    viewModel: CreateWalletViewModel = hiltViewModel()
) {

    val createWalletData = viewModel.createWalletFlow.collectAsState()
    val initialCreateWallet = CreateWalletEntity(
        limit = "100000",
        name = stringResource(id = R.string.wallet_text),
        currency = Currency.RUB
    )
    if (createWalletData.value == null) {
        viewModel.obtainEvent(CreateWalletViewModel.Event.InitCreateWallet(initialCreateWallet))
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
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.title_wallet),
                onValueChanged = {
                    viewModel.obtainEvent(CreateWalletViewModel.Event.UpdateNameWallet(it))
                },
                initialField = createWalletData.value?.name ?: initialCreateWallet.name
            )

            OutlinedButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.currency),
                text = stringResource(
                    id = createWalletData.value?.currency?.nameId
                        ?: initialCreateWallet.currency.nameId
                )
            ) {
                navigation.navigate(CurrenciesScreenFactory.route)
            }

            OutlinedEditText(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.limit_wallet),
                onValueChanged = {
                    viewModel.obtainEvent(CreateWalletViewModel.Event.UpdateLimitWallet(it))
                },
                initialField = createWalletData.value?.limit ?: initialCreateWallet.limit,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLength = 10
            )
        }
    }
}

class CreateWalletScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            CreateWalletScreen(navGraph)
        }
    }
}