package ru.alexbur.koshelok.data.mappers

import ru.alexbur.koshelok.data.service.api.UserApi
import ru.alexbur.koshelok.domain.entities.onboarding.UserEntity
import javax.inject.Inject

class UserEntityToUserApiMapper @Inject constructor() : (UserEntity) -> UserApi {

    override fun invoke(user: UserEntity): UserApi {
        return UserApi(
            email = user.email
        )
    }
}
