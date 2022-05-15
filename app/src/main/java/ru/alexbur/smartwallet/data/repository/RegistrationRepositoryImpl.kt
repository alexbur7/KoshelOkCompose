package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.UserEntityToUserApiMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.onboarding.UserEntity
import ru.alexbur.smartwallet.domain.repositories.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val userApiMapper: UserEntityToUserApiMapper
) : RegistrationRepository {

    override suspend fun registrationUser(user: UserEntity): Result<String> {
        return runCatching { appService.updateToken(userApiMapper(user)) }
    }
}
