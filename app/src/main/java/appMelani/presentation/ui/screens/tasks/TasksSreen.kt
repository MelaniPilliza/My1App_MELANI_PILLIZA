package appMelani.presentation.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import appMelani.domain.model.Task
import appMelani.presentation.viewmodel.tasks.TasksViewModel


@Composable
fun TaskScreen(viewModel: TasksViewModel) {
    // Obtiene el estado de las tareas desde el ViewModel utilizando collectAsState().value
    val tasks = viewModel.tasks.collectAsState().value

    // Inicializa una variable mutable 'taskName' para almacenar el nombre de la nueva tarea
    var taskName by remember { mutableStateOf("") }

    // Scaffold proporciona la estructura básica de la UI (como barra de herramientas, contenido, etc.)
    Scaffold { innerPadding ->

        // Column layout para organizar los elementos verticalmente
        Column(Modifier.padding(innerPadding)) {

            // Fila para el campo de texto y el botón para agregar una tarea
            Row(Modifier.fillMaxWidth()) {

                // TextField para capturar el nombre de la tarea
                TextField(
                    value = taskName,  // El valor del campo de texto es 'taskName'
                    onValueChange = { taskName = it }  // Se actualiza 'taskName' con el nuevo valor del campo
                )

                // Espaciador entre el campo de texto y el botón
                Spacer(modifier = Modifier.width(16.dp))

                // Botón que agrega una nueva tarea al presionar
                Button(onClick = {
                    viewModel.addTask(taskName)  // Llama al método addTask del ViewModel para agregar la tarea
                    taskName = ""  // Limpia el campo de texto después de agregar la tarea
                }) {
                    // Texto del botón
                    Text(text = "Agregar")
                }
            }

            // Espaciador para separar la sección de agregar tareas de la lista de tareas
            Spacer(modifier = Modifier.height(16.dp))

            // LazyColumn es un contenedor que permite mostrar una lista de forma eficiente (con desplazamiento)
            LazyColumn {

                // items() recibe la lista de tareas del ViewModel y las muestra una por una
                items(viewModel.tasks.value) { task ->
                    // Cada tarea se pasa como parámetro a TaskCard para su visualización
                    key(task) {
                        TaskCard(task = task, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, viewModel: TasksViewModel) {
    // Card es un contenedor visual que tiene un borde redondeado y sombra, usado para mostrar contenido
    Card(Modifier.fillMaxWidth()) {

        // Row se usa para alinear los elementos de forma horizontal
        Row {

            // Checkbox que indica si la tarea está completada o no
            Checkbox(
                checked = task.isCompleted,  // El estado del Checkbox depende de 'isCompleted' de la tarea
                onCheckedChange = { viewModel.toggleTaskCompletion(task.id) },  // Cambia el estado de la tarea al hacer clic en el Checkbox
                modifier = Modifier.align(Alignment.CenterVertically)  // Alinea el checkbox verticalmente al centro
            )

            // Texto que muestra el título de la tarea
            Text(
                task.title,  // Muestra el título de la tarea
                modifier = Modifier.align(Alignment.CenterVertically)  // Alinea el texto verticalmente al centro
            )

            // IconButton que contiene un icono para eliminar la tarea
            IconButton(onClick = { viewModel.removeTask(task.id) }) {
                // El icono de eliminar se muestra al hacer clic en el botón
                Icon(
                    imageVector = Icons.Default.Remove,  // Usa un icono de eliminar predeterminado
                    contentDescription = "Icono de eliminar"  // Descripción accesible para el icono
                )
            }
        }
    }
}
