package com.lord_ukaka.projectbymiracle.domain.models

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
