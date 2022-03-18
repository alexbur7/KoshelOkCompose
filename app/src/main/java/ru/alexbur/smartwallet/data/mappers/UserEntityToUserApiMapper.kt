package ru.alexbur.smartwallet.data.mappers

import ru.alexbur.smartwallet.data.service.api.UserApi
import ru.alexbur.smartwallet.domain.entities.onboarding.UserEntity
import javax.inject.Inject

class UserEntityToUserApiMapper @Inject constructor() : (UserEntity) -> UserApi {

    override fun invoke(user: UserEntity): UserApi {
        return UserApi(
            email = user.email
        )
    }
}
