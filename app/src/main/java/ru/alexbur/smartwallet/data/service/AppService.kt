package ru.alexbur.smartwallet.data.service

import retrofit2.http.*
import ru.alexbur.smartwallet.data.service.api.*

interface AppService {

    @GET("wallets")
    suspend fun getWallets(): List<WalletApi>

    @GET("transactions/{walletId}")
    suspend fun getTransaction(@Path("walletId") walletId: Long): List<TransactionApi>

    @POST("wallets")
    suspend fun createWallet(@Body walletApi: CreateWalletApi): WalletApi

    @PUT("wallets/{walletId}")
    suspend fun editWallet(
        @Path("walletId")
        id: Long,
        @Body walletApi: CreateWalletApi
    ): WalletApi

    @DELETE("wallets/{walletId}")
    suspend fun deleteWallet(@Path("walletId") walletId: Long): Boolean

    @GET("wallets/person/all")
    suspend fun getDataForMainScreen(): MainScreenDataApi

    @POST("transactions")
    suspend fun createTransaction(@Body transactionApi: CreateTransactionApi): TransactionApi

    @PUT("transactions/{transactionId}")
    suspend fun editTransaction(
        @Path("transactionId")
        id: Long,
        @Body transactionApi: CreateTransactionApi
    ): TransactionApi

    @DELETE("transactions/{transactionId}")
    suspend fun deleteTransaction(@Path("transactionId") id: Long): Boolean

    @GET("categories/person")
    suspend fun getCategories(): List<CategoryApi>

    @POST("categories")
    suspend fun createCategory(@Body categoryApi: CategoryApi): CategoryApi

    @GET("currencies")
    suspend fun getCurrencies(): List<CurrencyApi>
}