package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.mappers.UserEntityToUserApiMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.entities.onboarding.UserEntity
import ru.alexbur.koshelok.domain.repositories.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val userApiMapper: UserEntityToUserApiMapper
) : RegistrationRepository {

    override suspend fun registrationUser(user: UserEntity): Result<String> {
        return appService.updateToken(userApiMapper(user))
    }
}
