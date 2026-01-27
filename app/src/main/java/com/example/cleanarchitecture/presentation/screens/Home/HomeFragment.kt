package com.example.cleanarchitecture.presentation.screens.Home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cleanarchitecture.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val increase = view.findViewById<Button>(R.id.btnIncrease)
        val value = view.findViewById<TextView>(R.id.textView)

        viewModel.counter.observe(viewLifecycleOwner){mod ->
            value.text = mod.toString()
        }

        increase.setOnClickListener {
            viewModel.increaseValue()
        }
    }
}