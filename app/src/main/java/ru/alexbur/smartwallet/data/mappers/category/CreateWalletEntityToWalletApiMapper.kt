package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import javax.inject.Inject

class CreateWalletEntityToWalletApiMapper @Inject constructor() :
        (CreateWalletEntity) -> WalletApi {

    override operator fun invoke(
        createWalletEntity: CreateWalletEntity
    ): WalletApi =
        WalletApi(
            name = createWalletEntity.name,
            amountMoney = "0",
            income = "0",
            consumption = "0",
            limit = createWalletEntity.limit,
            currency = createWalletEntity.currency.name,
            isExceededLimit = false,
            isHide = false
        )
}
