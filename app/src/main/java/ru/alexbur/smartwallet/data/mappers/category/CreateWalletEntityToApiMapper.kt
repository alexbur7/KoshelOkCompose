package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.service.api.CreateWalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import javax.inject.Inject

class CreateWalletEntityToApiMapper @Inject constructor() :
        (CreateWalletEntity) -> CreateWalletApi {

    override operator fun invoke(
        createWalletEntity: CreateWalletEntity
    ): CreateWalletApi =
        CreateWalletApi(
            name = createWalletEntity.name,
            amountMoney = "0",
            income = "0",
            consumption = "0",
            limit = createWalletEntity.limit,
            currencyId = createWalletEntity.currency.id ?: 0,
            isHide = false
        )
}
