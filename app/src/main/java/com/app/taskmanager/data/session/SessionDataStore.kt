package com.app.taskmanager.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import com.app.taskmanager.data.model.User

private val Context.sessionDataStore by preferencesDataStore(name = "session")

class SessionDataStore(
    private val context: Context
) {

    private val userKey = stringPreferencesKey("user")

    suspend fun saveUser(user: User) {
        context.sessionDataStore.edit { preferences ->
            preferences[userKey] = Json.encodeToString(user)
        }
    }

    suspend fun getUser(): User? {
        val preferences = context.sessionDataStore.data.first()
        val json = preferences[userKey] ?: return null

        return Json.decodeFromString<User>(json)
    }

    suspend fun clearUser() {
        context.sessionDataStore.edit { preferences ->
            preferences.remove(userKey)
        }
    }
}