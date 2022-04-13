package ru.alexbur.smartwallet.data.service

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.alexbur.smartwallet.data.utils.AccountDataStore
import ru.alexbur.smartwallet.domain.entities.onboarding.UserEntity
import ru.alexbur.smartwallet.domain.repositories.RegistrationRepository
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val accountDataStore: AccountDataStore,
    private val registrationRepository: RegistrationRepository
) : Interceptor {

    private var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain
            .request()
            .newBuilder()

        runBlocking {
            token = accountDataStore.userToken.firstOrNull()
            if (token == null) {
                updateToken()
            }
        }

        val response = chain.proceed(builder.addHeader("Authorization", "Bearer $token").build())
        if (response.code != UNAUTHORIZED_ERROR_CODE) return response
        //GlobalScope.async {  }
        runBlocking {
            updateToken()
        }

        return chain.proceed(builder.addHeader("Authorization", "Bearer $token").build())
    }

    private suspend fun updateToken() {
        accountDataStore.name.firstOrNull()?.let {
            registrationRepository.registrationUser(UserEntity(it)).onSuccess { newToken ->
                accountDataStore.updateToken(newToken)
                token = newToken
            }
        }
    }

    private companion object {
        const val UNAUTHORIZED_ERROR_CODE = 401
    }
}
