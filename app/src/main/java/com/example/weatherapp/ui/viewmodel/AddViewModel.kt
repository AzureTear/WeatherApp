package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.FormAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddViewModel : ViewModel(){
    // StateFlow privado para mutar estado
    private val _formState = MutableStateFlow(FormAddState())

    // StateFlow público expuesto a la UI (solo lectura)
    val formState: StateFlow<FormAddState> = _formState

    // Manejo de cambios en "name"
    fun onNameChange(value: String) {
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "El nombre no puede estar vacío"
                value.length < 3 -> "Debe tener al menos 3 caracteres"
                else -> null
            }
            state.copy(
                name = value,
                nameError = error,
                isValid = validateForm(state.copy(name = value, nameError = error))
            )
        }
    }

    // Manejo de cambios en "description"
    fun onDescriptionChange(value: String) {
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "La descripción no puede estar vacía"
                else -> null
            }
            state.copy(
                description = value,
                descriptionError = error,
                isValid = validateForm(state.copy(description = value, descriptionError = error))
            )
        }
    }

    // Validación global del formulario
    private fun validateForm(state: FormAddState): Boolean {
        return state.nameError == null &&
                state.descriptionError == null &&
                state.name.isNotBlank() &&
                state.description.isNotBlank()
    }
}