package com.lord_ukaka.projectbymiracle.domain.usecases

import com.lord_ukaka.projectbymiracle.domain.models.ValidationResult

class ValidateEmail {

    operator fun invoke(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty!"
            )
        } else return ValidationResult(
            successful = true
        )
    }
}