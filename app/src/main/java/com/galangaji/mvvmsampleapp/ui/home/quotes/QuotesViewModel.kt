package com.galangaji.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.galangaji.mvvmsampleapp.data.repositories.QuotesRepository
import com.galangaji.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}