package com.galangaji.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.galangaji.mvvmsampleapp.data.db.AppDatabase
import com.galangaji.mvvmsampleapp.data.db.entities.Quote
import com.galangaji.mvvmsampleapp.data.network.MyApi
import com.galangaji.mvvmsampleapp.data.network.SafeApiRequest
import com.galangaji.mvvmsampleapp.data.preferences.PreferenceProvider
import com.galangaji.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSaveAt = prefs.getLastSaveAt()

        if (lastSaveAt == null || isFetchNeeded(LocalDateTime.parse(lastSaveAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }

    }

    private fun isFetchNeeded(saveAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(saveAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.savelastSaveAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}