package appMelani.presentation.viewmodel.tasks

import androidx.lifecycle.ViewModel
import appMelani.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasksViewModel : ViewModel() {
    // Estado mutable que almacena el listado de tareas
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    // Version inmutable del estado anterior, es el estado que va a leer el UI
    val tasks: StateFlow<List<Task>> = _tasks

    // Contador que hace de AUTO_INCREMENT en los id de tarea
    // Normalmente esto lo haría la base de datos
    private var idCounter = 0

    // Las siguientes funciones implementan alguna funcionalidad que provoca cambios en el estado del ViewModel

    // Añade una tarea
    fun addTask(title: String) {
        val newTask = Task(id = idCounter, title = title)
        _tasks.value = _tasks.value + newTask
        idCounter++
    }

    // Hace que una tarea cambie a completada
    fun toggleTaskCompletion(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(isCompleted = !task.isCompleted) else task
        }
    }

    // Elimina una tarea
    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }
}