package com.example.cleanarchitecture.presentation.screens.Home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomFragment : Fragment(R.layout.fragment_room) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get the DAO from DI
//        val userDAO = DatabaseModule.providerUserDAO(requireContext())

        //crate repository
//        val repository = UserRepositoryImpl(userDAO)

        //crate factory
//        val factory = RoomViewModelFactory(repository)

        //create viewModel now
        //val viewModel = ViewModelProvider(this, factory)[RoomViewModel::class.java]

        val viewModel: RoomViewModel by viewModels()

        val name = view.findViewById<EditText>(R.id.nameField)
        val age = view.findViewById<EditText>(R.id.ageField)
        val value = view.findViewById<TextView>(R.id.valuesId)
        val button = view.findViewById<Button>(R.id.buttonAdd)
        val delete = view.findViewById<Button>(R.id.deleteBtn)

        button.setOnClickListener {
            val name = name.text.toString()
            val age = age.text.toString()

            if (name.isNotBlank() && age.isNotBlank()) {
                viewModel.insertUser(
                    name = name, age = age.toInt()
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.collect { user ->
                value.text = user.joinToString {
                    "${it.name} - ${it.age}"
                }
            }
        }

        delete.setOnClickListener {
            val user = viewModel.user.value.firstOrNull() ?: return@setOnClickListener
            viewModel.deleteUser(user)
        }
    }
}