package ru.alexbur.koshelok.domain.entities.listwallet

import ru.alexbur.koshelok.domain.entities.wallet.WalletEntity

data class MainScreenDataEntity(
    val balanceEntity: BalanceEntity,
    val exchangeRatesEntity: ExchangeRatesEntity,
    val wallets: List<WalletEntity>
)