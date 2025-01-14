package com.example.appMelani.presentation.viewmodel.tasks

import androidx.lifecycle.ViewModel
import com.example.appMelani.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasksViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList()) // Estado mutable que almacena el listado de tareas
    val tasks: StateFlow<List<Task>> = _tasks // Version inmutable del estado anterior, es el estado que va a leer el UI

    // Contador que hace de AUTO_INCREMENT en los id de tarea
    private var idCounter = 0

    //TAREA AÑADIR
    fun addTask(title: String) {
        val newTask = Task(id = idCounter, title = title)
        _tasks.value = _tasks.value + newTask
        idCounter++
    }

    //TAREA COMPLETADA
    fun toggleTaskCompletion(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(isCompleted = !task.isCompleted) else task
        }
    }

    //TAREA ELIMINAR
    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }
}