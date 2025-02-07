package com.example.my1app_melani_pilliza.presentation.viewmodel.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.domain.model.User
import com.example.my1app_melani_pilliza.domain.usecase.users.DeleteUserUseCase
import com.example.my1app_melani_pilliza.domain.usecase.users.GetUsersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsersScreenViewModel(
    val getUsersUseCase: GetUsersUseCase,
    val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private var _users = getUsersUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val users: StateFlow<List<User>> = _users

    fun removeProduct(id: String) {
        viewModelScope.launch {
            deleteUserUseCase(id)
        }
    }
}