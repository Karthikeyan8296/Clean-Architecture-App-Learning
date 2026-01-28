package com.example.cleanarchitecture.presentation.screens.Home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecture.R
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val increase = view.findViewById<Button>(R.id.btnIncrease)
        val value = view.findViewById<TextView>(R.id.textView)

        //lifeCycleScope is tied with the fragment LifeCycle
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ){
                viewModel.counter.collect {
                    value.text = it.toString()
                }
            }
        }

        increase.setOnClickListener {
            viewModel.increaseValue()
        }
    }
}