package com.aqua30.local_preference.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.aqua30.local_preference.UserPreference
import com.aqua30.local_preference.data.KEYS.KEY_USER_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Saurabh Pant
 * @since 11/8/22
 */
class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
): UserPreference {

    override fun userName(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                preference[KEY_USER_NAME] ?: ""
            }
    }

    override suspend fun saveUserName(name: String) {
        dataStore.edit { preference ->
            preference[KEY_USER_NAME] = name
        }
    }
}

object KEYS {
    val KEY_USER_NAME = stringPreferencesKey("user_name")
}