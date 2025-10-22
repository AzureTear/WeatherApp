package com.example.weatherapp.data.model

data class FormAddState (
    val name: String = "",
    val nameError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val isValid: Boolean = false
)