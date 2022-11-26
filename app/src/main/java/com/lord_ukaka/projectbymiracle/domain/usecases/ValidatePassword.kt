package com.lord_ukaka.projectbymiracle.domain.usecases

import com.lord_ukaka.projectbymiracle.domain.models.ValidationResult

class ValidatePassword {

    operator fun invoke(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be empty!"
            )
        } else return ValidationResult(
            successful = true
        )
    }
}