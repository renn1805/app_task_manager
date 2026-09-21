package com.app.taskmanager.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.taskmanager.data.model.CondensedWorkspaceResponse
import com.app.taskmanager.data.model.SessionManager
import com.app.taskmanager.data.remote.ApiResult
import com.app.taskmanager.data.remote.WorkspaceService
import com.app.taskmanager.ui.login.LoginViewModel.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val workspaceService = WorkspaceService()
    data class WorkspacesState (
        val isLoading: Boolean = false,
        val workspaces: CondensedWorkspaceResponse? = null
    )

    private val _workspacesState = MutableStateFlow(WorkspacesState())
    val workspacesState: StateFlow<WorkspacesState> = _workspacesState

    fun loadWorkspaces() {
        viewModelScope.launch {
            _workspacesState.update {
                it.copy(isLoading = true)
            }

            when (val result = workspaceService.getWorkspaces(SessionManager.user.value!!.id)) {
                is ApiResult.Success -> {
                    _workspacesState.update {
                        it.copy(
                            isLoading = false,
                            workspaces = result.data
                        )
                    }
                }

                is ApiResult.Error -> {
                    _workspacesState.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }
}