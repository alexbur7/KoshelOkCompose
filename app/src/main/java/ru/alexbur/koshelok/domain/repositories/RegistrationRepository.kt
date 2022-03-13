package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.onboarding.UserEntity

interface RegistrationRepository {

    suspend fun registrationUser(user: UserEntity): Result<String>
}