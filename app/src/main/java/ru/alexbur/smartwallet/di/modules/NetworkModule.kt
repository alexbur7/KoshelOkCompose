package ru.alexbur.smartwallet.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.data.service.AuthInterceptor
import ru.alexbur.smartwallet.data.service.AuthService
import ru.alexbur.smartwallet.di.AllClient
import ru.alexbur.smartwallet.di.AllRetrofit
import ru.alexbur.smartwallet.di.AuthClient
import ru.alexbur.smartwallet.di.AuthRetrofit
import javax.inject.Singleton

private const val BASE_URL = "http://34.88.54.200:9090/"

@Module
@OptIn(ExperimentalSerializationApi::class)
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val contentType = "application/json".toMediaType()

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun provideConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @AllClient
    fun providesHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @AuthClient
    fun providesAuthHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @AllRetrofit
    @Provides
    fun providesRetrofit(
        converterFactory: Converter.Factory,
        @AllClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL).build()
    }

    @AuthRetrofit
    @Provides
    fun providesAuthRetrofit(
        converterFactory: Converter.Factory,
        @AuthClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL).build()
    }

    @Provides
    @Singleton
    fun providesApi(@AllRetrofit retrofit: Retrofit): AppService {
        /*return if (BuildConfig.DEBUG) {
            MockAppService()
        } else
            retrofit.create(AppService::class.java)*/
        return retrofit.create(AppService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthApi(@AuthRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}