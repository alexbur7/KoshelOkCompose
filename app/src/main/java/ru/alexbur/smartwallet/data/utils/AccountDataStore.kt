package ru.alexbur.smartwallet.data.utils

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
import ru.alexbur.smartwallet.R
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val AUTHORIZED_DATA_STORE_NAME = "authorized_data_store_name"
private val Context.dataStore by preferencesDataStore(AUTHORIZED_DATA_STORE_NAME)

class AccountDataStore @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    val email: Flow<String?>
        get() = preferences.map {
            it[EMAIL_KEY]
        }

    val name: Flow<String?>
        get() = preferences.map {
            it[NAME_KEY]
        }

    val userToken: Flow<String?>
        get() = preferences.map {
            it[USER_TOKEN_KEY]
        }

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

    suspend fun updateName(name: String?) {
        context.dataStore.edit { pref ->
            pref[NAME_KEY] = name ?: context.getString(R.string.unknown)
        }
    }

    suspend fun updateToken(userToken: String) {
        context.dataStore.edit { pref ->
            pref[USER_TOKEN_KEY] = userToken
        }
    }

    private companion object {
        val EMAIL_KEY = stringPreferencesKey("email_key")
        val NAME_KEY = stringPreferencesKey("name_key")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token_key")
    }
}