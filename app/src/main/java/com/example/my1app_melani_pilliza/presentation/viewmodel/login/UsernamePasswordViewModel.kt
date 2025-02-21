package com.example.my1app_melani_pilliza.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my1app_melani_pilliza.data.source.remote.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsernamePasswordViewModel(
    private val userRepository: UserRepository // Asegurar que se inyecta
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun clear() {
        _username.value = ""
        _password.value = ""
    }

    fun loginUser() {
        viewModelScope.launch {
            val result = userRepository.validateUser(username.value, password.value)
            _loginResult.value = result
        }
    }
}