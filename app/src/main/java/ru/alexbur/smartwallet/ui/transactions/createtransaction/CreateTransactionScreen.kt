package ru.alexbur.smartwallet.ui.transactions.createtransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.utils.OutlinedEditText
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor

@Composable
fun CreateTransactionScreen(
    walletId: Long,
    navController: NavController,
    viewModel: CreateTransactionViewModel = hiltViewModel()
) {
    val createWalletData = viewModel.createTransactionFlow.collectAsState()
    val initialCreateWallet = TransactionEntity(
        idWallet = walletId,
        currency = Currency.RUB,
        sum = "10000",
        type = TypeOperation.SELECT_INCOME,
        categoryEntity = null,
        date = System.currentTimeMillis()
    )
    if (createWalletData.value == null) {
        viewModel.obtainEvent(
            CreateTransactionViewModel.Event.InitCreateTransaction(
                initialCreateWallet
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(bottom = BottomNavigationHeight)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            CreateTransactionToolbar(
                modifier = Modifier.fillMaxWidth(),
                navController::popBackStack
            )

            OutlinedEditText(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                textLabel = stringResource(id = R.string.sum),
                initialField = createWalletData.value?.sum ?: initialCreateWallet.sum,
                onValueChanged = {
                    viewModel.obtainEvent(CreateTransactionViewModel.Event.UpdateSumTransaction(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedEditText(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                textLabel = stringResource(id = R.string.sum),
                initialField = createWalletData.value?.sum ?: initialCreateWallet.sum,
                onValueChanged = {
                    //viewModel.obtainEvent(CreateTransactionViewModel.Event.)
                }
            )

            OutlinedEditText(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                textLabel = stringResource(id = R.string.sum),
                initialField = createWalletData.value?.sum ?: initialCreateWallet.sum,
                onValueChanged = {
                    //viewModel.obtainEvent(CreateTransactionViewModel.Event.)
                }
            )
        }
    }
}