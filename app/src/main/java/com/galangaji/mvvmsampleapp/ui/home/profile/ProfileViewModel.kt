package com.galangaji.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.galangaji.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val user = repository.getUser()
}