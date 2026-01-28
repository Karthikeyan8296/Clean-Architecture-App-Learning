package com.example.cleanarchitecture.presentation.screens.Home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.R
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

//Create the data store
val Context.dataStore by preferencesDataStore(name = "AuthTokens")

//Type of the key is declared here
val TOKEN_KEY = stringPreferencesKey("token")

class UserFragment : Fragment(R.layout.fragment_user) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create shared preference
        val sharedPref = requireContext().getSharedPreferences("AuthToken", Context.MODE_PRIVATE)

        //Save/Edit - (key, value)
        sharedPref.edit().putString("token", "karthik123").apply()

        //Read - (key, default value)
        val value  = sharedPref.getString("token", "no token")

        //remove
        sharedPref.edit().remove("token").apply()

        //clear fully
        sharedPref.edit().clear().apply()


        lifecycleScope.launch {
            //Save/Edit
            requireContext().dataStore.edit { prefs->
                prefs[TOKEN_KEY] = "Karthik8296"
            }

            //Read, it uses flow
            val req = requireContext().dataStore.data.first() [TOKEN_KEY] ?: "token is null"
            view.findViewById<TextView>(R.id.txtToken).text = req

            //Remove
            requireContext().dataStore.edit { prefs->
                prefs.remove(TOKEN_KEY)
            }

            //Clear
            requireContext().dataStore.edit { prefs->
                prefs.remove(TOKEN_KEY)
            }
        }
    }
}