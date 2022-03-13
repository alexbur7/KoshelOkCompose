package ru.alexbur.koshelok.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.alexbur.koshelok.data.service.ApiResponseAdapter
import ru.alexbur.koshelok.data.service.AppService

private const val BASE_URL = "http://34.88.54.200:9090/"

@Module
@OptIn(ExperimentalSerializationApi::class)
@InstallIn(ViewModelComponent::class)
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
    fun providesHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    fun providesRetrofit(converterFactory: Converter.Factory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(ApiResponseAdapter.Factory())
            .baseUrl(BASE_URL).build()
    }

    @Provides
    fun providesApi(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }
}