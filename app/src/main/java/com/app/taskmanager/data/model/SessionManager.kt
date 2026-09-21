package com.app.taskmanager.data.model

import android.content.Context
import com.app.taskmanager.data.session.SessionDataStore

object SessionManager {

    private lateinit var dataStore: SessionDataStore

    var user: User? = null
        private set

    fun initialize(context: Context) {
        dataStore = SessionDataStore(context.applicationContext)
    }

    suspend fun login(user: User) {
        this.user = user
        dataStore.saveUser(user)
    }

    suspend fun logout() {
        user = null
        dataStore.clearUser()
    }

    suspend fun restoreSession() {
        user = dataStore.getUser()
    }
}