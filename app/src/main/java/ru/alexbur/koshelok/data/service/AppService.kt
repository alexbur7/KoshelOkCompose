package ru.alexbur.koshelok.data.service

import retrofit2.http.*
import ru.alexbur.koshelok.data.service.api.*

interface AppService {

    @GET("wallets/{walletId}")
    suspend fun getWallet(@Path("walletId") walletId: Long): Result<DetailWalletApi>

    @POST("wallets")
    suspend fun createWallet(@Body walletApi: WalletApi): Result<Long>

    @DELETE("wallets/{walletId}")
    suspend fun deleteWallet(@Path("walletId") walletId: Long): Result<Boolean>

    @GET("wallets/person/{personId}/all")
    suspend fun getDataForMainScreen(@Path("personId") personId: Long): Result<MainScreenDataApi>

    @POST("transactions")
    suspend fun createTransaction(@Body transactionApi: CreateTransactionApi): Result<Boolean>

    @GET("transactions/withCategory/{walletId}")
    suspend fun getTransactions(@Path("walletId") walletId: Long): Result<List<TransactionApi>>

    @PUT("transactions/{transactionId}")
    suspend fun editTransaction(
        @Path("transactionId")
        id: Long,
        @Body transactionApi: CreateTransactionApi
    ): Result<Boolean>

    @DELETE("transactions/{transactionId}")
    suspend fun deleteTransaction(@Path("transactionId") id: Long): Result<Boolean>

    @GET("categories/person/{personId}/{value}")
    suspend fun getCategories(
        @Path("personId") personId: Long,
        @Path("value") type: Int
    ): Result<List<CategoryApi>>

    @POST("categories")
    suspend fun createCategory(@Body categoryApi: CategoryApi): Result<Boolean>

    @POST("person")
    suspend fun updateToken(@Body userApi: UserApi): Result<String>
}