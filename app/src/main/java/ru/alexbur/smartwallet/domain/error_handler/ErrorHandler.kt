package ru.alexbur.smartwallet.domain.error_handler

interface ErrorHandler {

    fun handleError(error: Throwable): String
}