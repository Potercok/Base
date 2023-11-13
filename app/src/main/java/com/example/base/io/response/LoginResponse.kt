package com.example.base.io.response
import com.example.base.model.User
data class LoginResponse(
    val success: Boolean,
    val user: User,
    val jwt: String
)
