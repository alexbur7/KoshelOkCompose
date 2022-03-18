package ru.alexbur.smartwallet.data.extentions

suspend fun <T> resultRequest(method: suspend () -> T): Result<T> {
    return try {
        Result.success(method())
    } catch (e: Exception) {
        Result.failure(e)
    }
}