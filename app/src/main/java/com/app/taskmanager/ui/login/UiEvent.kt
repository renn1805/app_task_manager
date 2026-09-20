package com.app.taskmanager.ui.login

interface UiEvent {
    data class ShowMessage(val message: String) : UiEvent

}