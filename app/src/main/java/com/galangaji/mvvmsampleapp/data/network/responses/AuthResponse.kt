package com.galangaji.mvvmsampleapp.data.network.responses

import com.galangaji.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)