package com.csci448.kennylieu.kennylieu_A2.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.csci448.kennylieu.kennylieu_A2.MainActivity
import com.csci448.kennylieu.kennylieu_A2.R
import com.csci448.kennylieu.kennylieu_A2.ui.game.GameViewModel

class SettingsFragment : PreferenceFragmentCompat() {
    interface Callbacks {
        fun onSettingsBackClicked()
    }

    private val logTag = "448.SettingsFragment"

    private var callbacks: Callbacks? = null

    //private lateinit var viewModel: SettingsViewModel
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        Log.d(logTag, "onCreatePreferences() called")
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        val darkModeOn: SwitchPreferenceCompat? = findPreference("key_dark_mode")
        val xFirst: SwitchPreferenceCompat? = findPreference("key_x_first")

        xFirst?.setOnPreferenceChangeListener{ preference, newValue ->
            if(newValue == true){
                sharedPref.edit().putBoolean("x_first", true).apply()
                Toast.makeText(activity,"X goes first",Toast.LENGTH_SHORT).show()
                Log.d(logTag, "x goes first")
            } else {
                sharedPref.edit().putBoolean("x_first", false).apply()
                Toast.makeText(activity,"O goes first",Toast.LENGTH_SHORT).show()
                Log.d(logTag, "o goes first")
            }
            true
        }

        darkModeOn?.setOnPreferenceChangeListener{ preference, newValue -> //TODO rename preference
            if (newValue == true){
                Toast.makeText(activity,"Dark Mode enabled",Toast.LENGTH_SHORT).show()
                (activity as MainActivity).shouldEnableDarkMode(MainActivity.DarkModeConfig.YES)
                (activity as MainActivity).recreate() //recreates the MainActivity because the Preferences are stupid and won't dynamically change otherwise

            }else{
                Toast.makeText(activity,"Dark Mode disabled",Toast.LENGTH_SHORT).show()
                (activity as MainActivity).shouldEnableDarkMode(MainActivity.DarkModeConfig.NO)
                (activity as MainActivity).recreate() //recreates the MainActivity because the Preferences are stupid and won't dynamically change otherwise
            }
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")

        val factory = SettingsViewModelFactory(requireContext())
        settingsViewModel = ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called")
        super.onDetach()
        callbacks = null
    }

    override fun onStart(){
        Log.d(logTag, "onStart() called")
        super.onStart()
    }
    override fun onResume(){
        Log.d(logTag, "onResume() called")
        super.onResume()
    }
    override fun onPause(){
        Log.d(logTag, "onPause() called")
        super.onPause()
    }
    override fun onStop(){
        Log.d(logTag, "onStop() called")
        super.onStop()
        callbacks?.onSettingsBackClicked()
    }
    override fun onDestroy(){
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
    override fun onDestroyView(){
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    */

}