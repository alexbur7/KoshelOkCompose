package ru.alexbur.smartwallet.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import ru.alexbur.smartwallet.data.service.api.UserApi

interface AuthService {

    @POST("person")
    suspend fun updateToken(@Body userApi: UserApi): String
}