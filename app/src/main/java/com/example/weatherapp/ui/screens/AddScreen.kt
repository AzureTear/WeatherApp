package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.viewmodel.AddViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(vm: AddViewModel = viewModel()) {
    // Obtenemos el estado en tiempo real desde StateFlow
    val state = vm.formState.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Nuevo Item") })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Campo nombre
            OutlinedTextField(
                value = state.name,
                onValueChange = { vm.onNameChange(it) },
                label = { Text("Nombre") },
                isError = state.nameError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (state.nameError != null) {
                Text(
                    text = state.nameError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Campo descripción
            OutlinedTextField(
                value = state.description,
                onValueChange = { vm.onDescriptionChange(it) },
                label = { Text("Descripción") },
                isError = state.descriptionError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (state.descriptionError != null) {
                Text(
                    text = state.descriptionError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de guardar habilitado solo si el form es válido
            Button(
                onClick = { /* Guardar en BD o Firebase */ },
                enabled = state.isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}