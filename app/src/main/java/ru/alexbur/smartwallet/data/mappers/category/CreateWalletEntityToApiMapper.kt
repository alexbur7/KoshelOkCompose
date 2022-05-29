package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.extentions.defaultMoney
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
            amountMoney = createWalletEntity.amountMoney.defaultMoney(),
            income = createWalletEntity.incomeMoney.defaultMoney(),
            consumption = createWalletEntity.consumptionMoney.defaultMoney(),
            limit = createWalletEntity.limit,
            currencyId = createWalletEntity.currency.id ?: 0,
            isHide = false
        )
}
