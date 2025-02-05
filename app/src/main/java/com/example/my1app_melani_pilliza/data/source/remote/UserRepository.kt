package com.example.my1app_melani_pilliza.data.source.remote

import com.example.my1app_melani_pilliza.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserRepository(val firestore: FirebaseFirestore) {

    private val usersCollection = firestore.collection("users")

    // Obtener un usuario por ID
    suspend fun getUserById(id: String): User? {
        return try {
            val documentSnapshot = usersCollection.document(id).get().await()
            documentSnapshot.toObject(User::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun list(): Flow<List<User>> {
        // Esta implementación crea un Flow que actualiza la lista de usuarios
        // cada vez que hay un cambio en la base de datos
        return callbackFlow {

            val listener = usersCollection
                // Aquí viene la query,
                // Se ordena por nombre de manera desceniente
                .orderBy("name", Query.Direction.DESCENDING)
                // Creamos un listener a la query para que se actualice siempre que haya cambios
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val items = snapshots?.documents?.mapNotNull { doc ->
                        doc.toObject(User::class.java)
                    } ?: emptyList()

                    trySend(items)
                }

            awaitClose() { listener.remove() }
        }
    }

    // Agregar un nuevo usuario
    suspend fun addUser(user: User): Boolean {
        return try {
            usersCollection.add(user).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Actualizar un usuario existente
    suspend fun updateUser(id: String, user: Map<String, Any>): Boolean {
        return try {
            usersCollection.document(id).update(user).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Eliminar un usuario por ID
    suspend fun deleteUser(id: String): Boolean {
        return try {
            usersCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}