package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

class SavingDataManagerImpl @Inject constructor() : SavingDataManager {
    override val createWalletFlow: MutableStateFlow<CreateWalletEntity> = MutableStateFlow(
        CreateWalletEntity.default
    )
    override val editWalletFlow: MutableStateFlow<WalletEntity?> = MutableStateFlow(null)

    override val createTransactionFlow: MutableStateFlow<TransactionEntity?> =
        MutableStateFlow(null)

    override val editTransactionFLow: MutableStateFlow<TransactionEntity?> = MutableStateFlow(null)

    override val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?> =
        MutableStateFlow(null)

    override val walletIdFlow: MutableStateFlow<Long> =
        MutableStateFlow(-1L)
    override val walletFlow: MutableStateFlow<WalletEntity?> = MutableStateFlow(null)

    override val categoriesFlow: MutableStateFlow<List<CategoryEntity>> =
        MutableStateFlow(emptyList())

    override val createCategoryFlow: MutableStateFlow<CategoryEntity> = MutableStateFlow(
        CategoryEntity.defaultCategory
    )
    override val snackBarMessageFlow: MutableSharedFlow<String> = MutableSharedFlow()
}