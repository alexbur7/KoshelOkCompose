package ru.alexbur.smartwallet.domain.repositories

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState

interface SavingDataManager {
    val createWalletFlow: MutableStateFlow<CreateWalletEntity>

    val editWalletFlow: MutableStateFlow<WalletEntity?>

    val createTransactionFlow: MutableStateFlow<TransactionEntity?>

    val editTransactionFLow: MutableStateFlow<TransactionEntity?>

    val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?>

    val walletIdFlow: MutableStateFlow<Long>

    val walletFlow: MutableStateFlow<WalletEntity?>

    val categoriesFlow: MutableStateFlow<List<CategoryEntity>>

    val createCategoryFlow: MutableStateFlow<CategoryEntity>

    val snackBarMessageFlow: MutableSharedFlow<String>

    val loadingStateFlow: MutableSharedFlow<LoadingState>
}