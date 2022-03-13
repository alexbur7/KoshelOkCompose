package ru.alexbur.koshelok.data.service

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.SocketTimeoutException

class ApiResponseAdapter<R>(private val responseType: Type) : CallAdapter<R, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Any {
        return try {
            val response = call.execute()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response)
            } else {
                Result.failure(exception = SocketTimeoutException())
            }
        } catch (e: Exception) {
            Result.failure(exception = Exception(e.localizedMessage))
        }
    }

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type?,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            return returnType?.let {
                return try {
                    val enclosingType = (it as ParameterizedType)

                    if (enclosingType.rawType != Result::class.java)
                        null
                    else {
                        val type = enclosingType.actualTypeArguments[0]
                        ApiResponseAdapter<Any>(type)
                    }
                } catch (ex: ClassCastException) {
                    null
                }
            }

        }
    }
}