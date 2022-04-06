package ru.alexbur.smartwallet.ui.transactions.createtransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.data.extentions.getCalendar
import ru.alexbur.smartwallet.data.extentions.getDayWithMonth
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesScreenFactory
import ru.alexbur.smartwallet.ui.utils.OutlinedButton
import ru.alexbur.smartwallet.ui.utils.OutlinedEditText
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency.CurrenciesScreenFactory
import javax.inject.Inject

@Composable
fun CreateTransactionScreen(
    walletId: Long,
    navController: NavController,
    viewModel: CreateTransactionViewModel = hiltViewModel()
) {

    val dialogState = rememberMaterialDialogState()

    val createTransactionData = viewModel.createTransactionFlow.collectAsState()
    val initialCreateTransaction = TransactionEntity(
        idWallet = walletId,
        currency = Currency.RUB,
        sum = "10000",
        type = TypeOperation.SELECT_INCOME,
        categoryEntity = CategoryEntity(
            0,
            TypeOperation.SELECT_INCOME,
            stringResource(id = R.string.supermarket_text),
            0
        ),
        date = System.currentTimeMillis()
    )
    if (createTransactionData.value == null) {
        viewModel.obtainEvent(
            CreateTransactionViewModel.Event.InitCreateTransaction(
                initialCreateTransaction
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
                .padding(24.dp)
        ) {
            CreateTransactionToolbar(
                modifier = Modifier.fillMaxWidth(),
                navController::popBackStack
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 24.dp),
                text = stringResource(id = R.string.operations),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700)
                )
            )

            OutlinedEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textLabel = stringResource(id = R.string.sum),
                initialField = createTransactionData.value?.sum ?: initialCreateTransaction.sum,
                onValueChanged = {
                    viewModel.obtainEvent(CreateTransactionViewModel.Event.UpdateSumTransaction(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            TypeOperationChooser(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textLabel = stringResource(id = R.string.type),
                currentOperationType = createTransactionData.value?.type
                    ?: initialCreateTransaction.type
            ) {
                viewModel.obtainEvent(CreateTransactionViewModel.Event.UpdateTypeOperation(it))
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textLabel = stringResource(id = R.string.category),
                text = createTransactionData.value?.categoryEntity?.operation
                    ?: initialCreateTransaction.categoryEntity.operation
            ) {
                //navController.navigate(CategoriesScreenFactory.route)
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 24.dp),
                text = stringResource(id = R.string.edition_settings),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700)
                )
            )

            OutlinedButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.currency),
                text = stringResource(
                    id = createTransactionData.value?.currency?.nameId
                        ?: initialCreateTransaction.currency.nameId
                )
            ) {
                navController.navigate(CurrenciesScreenFactory.route)
            }

            OutlinedButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent),
                textLabel = stringResource(id = R.string.date_operation),
                text =
                createTransactionData.value?.date?.getCalendar()
                    ?.getDayWithMonth(LocalContext.current)
                    ?: initialCreateTransaction.date.getCalendar()
                        .getDayWithMonth(LocalContext.current)
            ) {
                dialogState.show()
            }
        }

        MaterialDialog(
            dialogState = dialogState,
            buttons = {
                positiveButton("Ok")
                negativeButton("Cancel")
            }
        ) {
            /*datepicker { date ->
                viewModel.obtainEvent(CreateTransactionViewModel.Event.UpdateDate(date))
            }*/
        }
    }
}

class CreateTransactionScreenFactory @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationFactory.NavigationFactoryCompanion {
        private const val WALLET_ID_KEY = "walletId"
    }

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = "$route/{${
                WALLET_ID_KEY
            }}",
            arguments = listOf(navArgument(WALLET_ID_KEY) { type = NavType.LongType })
        ) {
            it.arguments?.getLong(WALLET_ID_KEY)?.let { walletId ->
                CreateTransactionScreen(
                    walletId = walletId,
                    navController = navGraph,
                )
            }
        }
    }

}