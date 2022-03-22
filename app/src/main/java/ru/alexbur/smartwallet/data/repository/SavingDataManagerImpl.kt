package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

//TODO подумать над значениями по умолчанию
class SavingDataManagerImpl @Inject constructor() : SavingDataManager {
    override val createWalletFlow: MutableStateFlow<CreateWalletEntity> = MutableStateFlow(
        CreateWalletEntity(
            limit = "100000",
            name = "Кошелек 1",
            currency = Currency.RUB
        )
    )

    override val createTransactionFlow: MutableStateFlow<TransactionEntity?> =
        MutableStateFlow(null)

    override val walletFlow: MutableStateFlow<WalletEntity?> = MutableStateFlow(null)

    override val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?> =
        MutableStateFlow(null)
}