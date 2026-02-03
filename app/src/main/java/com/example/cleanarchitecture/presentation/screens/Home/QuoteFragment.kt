package com.example.cleanarchitecture.presentation.screens.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.w3c.dom.Text


@AndroidEntryPoint
class QuoteFragment : Fragment(R.layout.fragment_quote) {

    val viewModel: QuoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadQuoteNext()

        val textQuote = view.findViewById<TextView>(R.id.txtQuote)
        val textAuthor = view.findViewById<TextView>(R.id.txtAuthor)
        val randomBtn = view.findViewById<TextView>(R.id.randomBtn)

        randomBtn.setOnClickListener {
            viewModel.loadQuoteNext()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                textQuote.text = viewModel.state.value.quote
                textAuthor.text = viewModel.state.value.author
            }
        }
    }

}