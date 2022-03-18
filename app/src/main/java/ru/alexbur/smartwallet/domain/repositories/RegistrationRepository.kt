package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.onboarding.UserEntity

interface RegistrationRepository {

    suspend fun registrationUser(user: UserEntity): Result<String>
}