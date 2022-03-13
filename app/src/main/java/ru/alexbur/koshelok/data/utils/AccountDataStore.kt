package ru.alexbur.koshelok.data.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class AccountDataStore @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    val email: Flow<String?>
        get() = preferences.map {
            it[EMAIL_KEY]
        }

    val userToken: Flow<String?>
        get() = preferences.map {
            it[USER_TOKEN_KEY]
        }

    private val Context.dataStore by preferencesDataStore(AUTHORIZED_DATA_STORE_NAME)

    private val preferences: Flow<Preferences> =
        context.dataStore.data.catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }

    suspend fun updateEmail(email: String) {
        context.dataStore.edit { pref ->
            pref[EMAIL_KEY] = email
        }
    }

    suspend fun updateToken(userToken: String) {
        context.dataStore.edit { pref ->
            pref[USER_TOKEN_KEY] = userToken
        }
    }

    private companion object {
        const val AUTHORIZED_DATA_STORE_NAME = "authorized_data_store_name"
        val EMAIL_KEY = stringPreferencesKey("email_key")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token_key")
    }
}