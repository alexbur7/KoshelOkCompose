package ru.alexbur.smartwallet.data.service

import retrofit2.http.*
import ru.alexbur.smartwallet.data.service.api.*

interface AppService {

    @GET("wallets/{walletId}")
    suspend fun getWallet(@Path("walletId") walletId: Long): DetailWalletApi

    @POST("wallets")
    suspend fun createWallet(@Body walletApi: WalletApi): WalletApi

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

    @GET("categories/person/{value}")
    suspend fun getCategories(
        @Path("value") type: Int
    ): List<CategoryApi>

    @POST("categories")
    suspend fun createCategory(@Body categoryApi: CategoryApi): CategoryApi

    @POST("person")
    suspend fun updateToken(@Body userApi: UserApi): String
}