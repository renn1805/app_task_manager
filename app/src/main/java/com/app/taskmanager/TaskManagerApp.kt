package com.app.taskmanager

import android.app.Application
import com.app.taskmanager.data.model.SessionManager
import com.app.taskmanager.data.session.SessionDataStore

class TaskManagerApplication  : Application() {

    lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()

        sessionManager = SessionManager(
            SessionDataStore(this)
        )
    }
}