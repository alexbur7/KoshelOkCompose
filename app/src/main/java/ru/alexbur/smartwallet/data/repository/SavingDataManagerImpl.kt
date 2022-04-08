package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

class SavingDataManagerImpl @Inject constructor() : SavingDataManager {
    override val createWalletFlow: MutableStateFlow<CreateWalletEntity?> = MutableStateFlow(
        null
    )

    override val createTransactionFlow: MutableStateFlow<TransactionEntity?> =
        MutableStateFlow(null)

    override val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?> =
        MutableStateFlow(null)

    override val walletIdFlow: MutableStateFlow<Long> =
        MutableStateFlow(-1)

    override val categoriesFlow: MutableStateFlow<List<CategoryEntity>> =
        MutableStateFlow(emptyList())

    override val createCategoryFlow: MutableStateFlow<CategoryEntity> = MutableStateFlow(
        CategoryEntity(
            type = TypeOperation.SELECT_INCOME,
            operation = "",
            iconId = R.drawable.capitalization
        )
    )
}