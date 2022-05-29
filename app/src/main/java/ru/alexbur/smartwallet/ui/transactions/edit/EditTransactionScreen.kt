package ru.alexbur.smartwallet.ui.transactions.edit

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.data.extentions.getCalendar
import ru.alexbur.smartwallet.data.extentions.getDayWithMonth
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.CurrencyScreenType
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesScreenFactory
import ru.alexbur.smartwallet.ui.transactions.createtransaction.TypeOperationChooser
import ru.alexbur.smartwallet.ui.utils.CircleProgressBar
import ru.alexbur.smartwallet.ui.utils.OutlinedButton
import ru.alexbur.smartwallet.ui.utils.OutlinedEditText
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.create.listcurrency.CurrenciesScreenFactory
import java.util.*
import javax.inject.Inject

@Composable
fun EditTransactionScreen(
    walletId: Long,
    navController: NavController,
    viewModel: EditTransactionViewModel = hiltViewModel()
) {
    val categoriesState = viewModel.categoriesFlow.collectAsState()
    val createTransactionData = viewModel.createTransactionFlow.collectAsState()
    val initialCreateTransaction = TransactionEntity(
        idWallet = walletId,
        currency = CurrencyEntity.default,
        type = TypeOperation.SELECT_INCOME,
        sum = "10000",
        categoryEntity = CategoryEntity(
            0,
            TypeOperation.SELECT_INCOME,
            stringResource(id = R.string.supermarket_text),
            0
        ),
        date = System.currentTimeMillis()
    )

    val errorMessage = viewModel.errorMessage.collectAsState("")
    val snackBarHostState = SnackbarHostState()
    val isVisibleProgressBar = viewModel.isVisibleProgressBarFlow.collectAsState(initial = false)

    LaunchedEffect(key1 = errorMessage.value, categoriesState.value) {
        if (errorMessage.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                message = errorMessage.value
            )
        }
        if (categoriesState.value.isNotEmpty() && createTransactionData.value == null) {
            viewModel.obtainEvent(
                EditTransactionViewModel.Event.InitCreateTransaction(
                    initialCreateTransaction.copy(categoryEntity = categoriesState.value.first
                    { it.type == TypeOperation.SELECT_INCOME })
                )
            )
        }
    }

    val datePickerDialog =
        createDatePickerDialog(viewModel, createTransactionData, initialCreateTransaction)

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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.edit_operation_title),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                ),
                textAlign = TextAlign.Center
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
                    viewModel.obtainEvent(EditTransactionViewModel.Event.UpdateSumTransaction(it))
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
                viewModel.obtainEvent(EditTransactionViewModel.Event.UpdateTypeOperation(it))
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textLabel = stringResource(id = R.string.category),
                text = createTransactionData.value?.categoryEntity?.operation
                    ?: initialCreateTransaction.categoryEntity.operation
            ) {
                if (categoriesState.value.isNotEmpty()) {
                    navController.navigate(
                        "${CategoriesScreenFactory.route}/${
                            createTransactionData.value?.type?.code
                                ?: initialCreateTransaction.type.code
                        }"
                    )
                }
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
                text = createTransactionData.value?.currency?.fullName
                    ?: initialCreateTransaction.currency.fullName
            ) {
                navController.navigate("${CurrenciesScreenFactory.route}/${CurrencyScreenType.EDIT_TRANSACTION_SCREEN.code}")
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
                datePickerDialog.show()
            }
        }

        SnackbarHost(hostState = snackBarHostState) {
            SmartWalletSnackBar(snackbarData = it)
        }

        CircleProgressBar(
            modifier = Modifier.align(Alignment.Center),
            isVisible = isVisibleProgressBar.value
        )
    }
}

@Composable
private fun createDatePickerDialog(
    viewModel: EditTransactionViewModel,
    createTransactionData: State<TransactionEntity?>,
    initialCreateTransaction: TransactionEntity
) = DatePickerDialog(
    LocalContext.current,
    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        val cal = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)
        viewModel.obtainEvent(EditTransactionViewModel.Event.UpdateDate(cal.time.time))
    },
    createTransactionData.value?.date?.getCalendar()?.get(Calendar.YEAR)
        ?: initialCreateTransaction.date.getCalendar().get(Calendar.YEAR),
    createTransactionData.value?.date?.getCalendar()?.get(Calendar.MONTH)
        ?: initialCreateTransaction.date.getCalendar().get(Calendar.MONTH),
    createTransactionData.value?.date?.getCalendar()?.get(Calendar.DAY_OF_MONTH)
        ?: initialCreateTransaction.date.getCalendar().get(Calendar.DAY_OF_MONTH)
)

class EditTransactionScreenFactory @Inject constructor() : NavigationScreenFactory {
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
                EditTransactionScreen(
                    walletId = walletId,
                    navController = navGraph,
                )
            }
        }
    }
}