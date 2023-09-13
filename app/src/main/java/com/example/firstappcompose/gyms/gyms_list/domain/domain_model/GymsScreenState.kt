package com.example.firstappcompose.gyms.gyms_list.domain.domain_model

data class GymsScreenState(
    val gyms: List<GymsData>,
    val isLoading: Boolean,
    val errorMessage : String? = null
)