package com.lord_ukaka.projectbymiracle.presentation.login

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
