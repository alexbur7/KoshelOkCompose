package ru.alexbur.smartwallet.data.service

import retrofit2.http.*
import ru.alexbur.smartwallet.data.service.api.*

interface AppService {

    @GET("wallets")
    suspend fun getWallets(): ResponseApi<List<WalletApi>>

    @GET("transactions/{Id}")
    suspend fun getTransaction(@Path("Id") walletId: Long): ResponseApi<List<TransactionApi>>

    @POST("wallets")
    suspend fun createWallet(@Body walletApi: CreateWalletApi): ResponseApi<WalletApi>

    @PUT("wallets/{walletId}")
    suspend fun editWallet(
        @Path("walletId")
        id: Long,
        @Body walletApi: CreateWalletApi
    ): ResponseApi<WalletApi>

    @DELETE("wallets/{walletId}")
    suspend fun deleteWallet(@Path("walletId") walletId: Long): ResponseApi<Boolean>

    @GET("mainscreen")
    suspend fun getDataForMainScreen(): ResponseApi<MainScreenDataApi>

    @POST("transactions")
    suspend fun createTransaction(@Body transactionApi: CreateTransactionApi): ResponseApi<TransactionApi>

    @PUT("transactions/{transactionId}")
    suspend fun editTransaction(
        @Path("transactionId")
        id: Long,
        @Body transactionApi: CreateTransactionApi
    ): ResponseApi<TransactionApi>

    @DELETE("transactions/{transactionId}")
    suspend fun deleteTransaction(@Path("transactionId") id: Long): ResponseApi<Boolean>

    @GET("categories")
    suspend fun getCategories(): ResponseApi<List<CategoryApi>>

    @POST("categories")
    suspend fun createCategory(@Body categoryApi: CategoryApi): ResponseApi<CategoryApi>

    @GET("currencies")
    suspend fun getCurrencies(): ResponseApi<List<CurrencyApi>>
}