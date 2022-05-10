package ru.alexbur.smartwallet.data.error_handler

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import java.net.ConnectException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : ErrorHandler {

    override fun handleError(error: Throwable): String {
        return when (error) {
            is HttpException -> {
                handleHttpError(error = error)
            }
            is ConnectException -> {
                context.getString(R.string.internet_error)
            }
            else -> context.getString(R.string.unknown_error)
        }
    }

    private fun handleHttpError(error: HttpException): String {
        return when (error.code()) {
            401 -> context.getString(R.string.auth_error)
            else -> error.message()
        }
    }
}