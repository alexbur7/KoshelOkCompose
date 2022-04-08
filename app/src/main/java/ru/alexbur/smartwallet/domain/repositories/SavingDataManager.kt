package ru.alexbur.smartwallet.domain.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity

interface SavingDataManager {
    val createWalletFlow: MutableStateFlow<CreateWalletEntity?>

    val createTransactionFlow: MutableStateFlow<TransactionEntity?>

    val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?>

    val walletIdFlow: MutableStateFlow<Long>

    val categoriesFlow: MutableStateFlow<List<CategoryEntity>>

    val createCategoryFlow: MutableStateFlow<CategoryEntity>
}