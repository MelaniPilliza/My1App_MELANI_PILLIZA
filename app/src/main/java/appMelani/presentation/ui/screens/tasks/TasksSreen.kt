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
import androidx.lifecycle.viewmodel.compose.viewModel
import appMelani.domain.model.Task
import appMelani.presentation.viewmodel.tasks.TasksViewModel


@Composable
fun TaskScreen(tasksViewModel: TasksViewModel = viewModel()) {
    val tasks = tasksViewModel.tasks.collectAsState().value // Obtiene el estado de las tareas desde el ViewModel utilizando collectAsState().value
    var taskName by remember { mutableStateOf("") } // Inicializa una variable mutable 'taskName' para almacenar el nombre de la nueva tarea

    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = taskName,
                    onValueChange = { taskName = it }  // Se actualiza 'taskName' con el nuevo valor del campo
                )
                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    tasksViewModel.addTask(taskName)
                    taskName = ""
                }) {
                    Text(text = "Agregar")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tasksViewModel.tasks.value) { task ->
                    key(task) {
                        TaskCard(task = task, viewModel = tasksViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, viewModel: TasksViewModel) {
    Card(Modifier.fillMaxWidth()) {
        Row {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { viewModel.toggleTaskCompletion(task.id) },
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            // Texto que muestra el título de la tarea
            Text(
                task.title,
                modifier = Modifier.align(
                    Alignment.CenterVertically)
            )

            IconButton(onClick = { viewModel.removeTask(task.id) }) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}
