package com.example.my1app_melani_pilliza.domain.usecase.users

import com.example.my1app_melani_pilliza.data.source.remote.UserRepository
import com.example.my1app_melani_pilliza.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val userRepository: UserRepository) {
    // Implementamos la lógica del caso de uso dentro de este método
    operator fun invoke(): Flow<List<User>> {
        // Lógica del caso de uso
        return userRepository.list()
    }
}