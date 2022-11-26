package com.lord_ukaka.projectbymiracle.domain.models

import com.lord_ukaka.projectbymiracle.domain.usecases.ValidateEmail
import com.lord_ukaka.projectbymiracle.domain.usecases.ValidatePassword

data class Validations(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
