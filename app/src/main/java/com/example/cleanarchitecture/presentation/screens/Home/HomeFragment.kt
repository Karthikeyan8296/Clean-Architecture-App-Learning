package com.example.cleanarchitecture.presentation.screens.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitecture.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {


    //can only create viewModel with empty constructor!!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        view.findViewById<Button>(R.id.btnContinue).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserFragment())
                //name any - its is used for debugging
                .addToBackStack("user Transaction")
                //it starts the life cycle
                .commit()
        }

        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RoomFragment())
                .addToBackStack(null)
                .commit()
        }

        val increase = view.findViewById<Button>(R.id.btnIncrease)
        val value = view.findViewById<TextView>(R.id.textView)

        //lifeCycleScope is tied with the fragment LifeCycle
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                viewModel.counter.collect {
                    value.text = it.toString()
                }
            }
        }
        increase.setOnClickListener {
            viewModel.increaseValue()
        }

        val number: Flow<Int> = flow {
            repeat(20) {
                emit(1 + it)
                delay(1000)
            }
        }

        lifecycleScope.launch {
            number.collect {
                Log.d("values", "the values: $it")
            }
        }

        val message = Channel<Int>()
        //producer
        lifecycleScope.launch {
            message.send(1)
            message.send(2)
        }
        //consumer
        lifecycleScope.launch {
            Log.d("hoo", "channel values ${message.receive()}")
        }
    }
}