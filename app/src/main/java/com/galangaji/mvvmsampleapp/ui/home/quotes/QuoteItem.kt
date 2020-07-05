package com.galangaji.mvvmsampleapp.ui.home.quotes

import com.galangaji.mvvmsampleapp.R
import com.galangaji.mvvmsampleapp.data.db.entities.Quote
import com.galangaji.mvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}