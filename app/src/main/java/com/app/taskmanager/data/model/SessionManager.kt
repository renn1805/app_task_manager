package com.app.taskmanager.data.model

import com.app.taskmanager.data.session.SessionDataStore

class SessionManager(
    private val dataStore: SessionDataStore
) {

    var user: User? = null
        private set

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