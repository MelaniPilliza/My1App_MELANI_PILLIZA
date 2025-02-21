package com.example.my1app_melani_pilliza.presentation.viewmodel.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.domain.model.User
import com.example.my1app_melani_pilliza.data.source.remote.UserRepository
import com.example.my1app_melani_pilliza.domain.usecase.users.DeleteUserUseCase
import com.example.my1app_melani_pilliza.domain.usecase.users.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsersScreenViewModel(
    private val userRepository: UserRepository,
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private var _users = getUsersUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val users: StateFlow<List<User>> = _users

    fun addUser(user: User, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = userRepository.addUser(user.copy(password = user.password))
            onResult(success)
        }
    }

    fun removeUser(id: String) {
        viewModelScope.launch {
            deleteUserUseCase(id)
        }
    }
}