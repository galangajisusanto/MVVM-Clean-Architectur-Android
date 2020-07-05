package com.galangaji.mvvmsampleapp.ui.auth

import com.galangaji.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}