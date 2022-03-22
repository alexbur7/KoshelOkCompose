package ru.alexbur.smartwallet.domain.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.entities.listwallet.CurrencyEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency

interface SavingDataManager {
    val createWalletFlow: MutableStateFlow<CreateWalletEntity>

    val createTransactionFlow: MutableStateFlow<TransactionEntity?>

    val walletFlow: MutableStateFlow<WalletEntity?>

    val transactionFlow: MutableStateFlow<DetailWalletItem.Transaction?>
}