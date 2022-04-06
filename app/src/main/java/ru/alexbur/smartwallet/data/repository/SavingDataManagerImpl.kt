package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
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
}