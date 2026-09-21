package com.app.taskmanager.data.model

import android.content.Context
import com.app.taskmanager.data.session.SessionDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SessionManager {

    private lateinit var dataStore: SessionDataStore

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun initialize(context: Context) {
        dataStore = SessionDataStore(context.applicationContext)
    }

    suspend fun login(user: User) {
        _user.value = user
        dataStore.saveUser(user)
    }

    suspend fun logout() {
        _user.value = null
        dataStore.clearUser()
    }

    suspend fun restoreSession() {
        _user.value = dataStore.getUser()
    }
}