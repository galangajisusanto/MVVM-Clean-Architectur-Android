package com.galangaji.mvvmsampleapp.data.network.responses

import com.galangaji.mvvmsampleapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)