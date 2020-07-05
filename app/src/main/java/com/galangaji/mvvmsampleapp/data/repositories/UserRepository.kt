package com.galangaji.mvvmsampleapp.data.repositories

import com.galangaji.mvvmsampleapp.data.db.AppDatabase
import com.galangaji.mvvmsampleapp.data.db.entities.User
import com.galangaji.mvvmsampleapp.data.network.MyApi
import com.galangaji.mvvmsampleapp.data.network.SafeApiRequest
import com.galangaji.mvvmsampleapp.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignUp(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upSert(user)

    fun getUser() = db.getUserDao().getUser()


}