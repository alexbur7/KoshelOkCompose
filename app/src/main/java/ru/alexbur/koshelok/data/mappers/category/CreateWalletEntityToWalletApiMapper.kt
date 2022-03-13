package ru.alexbur.koshelok.data.mappers.category

import ru.alexbur.koshelok.data.service.api.WalletApi
import ru.alexbur.koshelok.domain.entities.wallet.CreateWalletEntity
import javax.inject.Inject

class CreateWalletEntityToWalletApiMapper @Inject constructor() :
        (Long?, Long, CreateWalletEntity) -> WalletApi {

    override operator fun invoke(
        id: Long?,
        personId: Long,
        createWalletEntity: CreateWalletEntity
    ): WalletApi =
        WalletApi(
            id = id,
            name = createWalletEntity.name,
            amountMoney = "0",
            income = "0",
            consumption = "0",
            limit = createWalletEntity.limit,
            currency = createWalletEntity.currency.name,
            personId = personId,
            isExceededLimit = false,
            isHide = false
        )
}
