package com.csci448.kennylieu.kennylieu_A2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.csci448.kennylieu.kennylieu_A2.ui.game.GameFragment
import com.csci448.kennylieu.kennylieu_A2.ui.history.HistoryFragment
import com.csci448.kennylieu.kennylieu_A2.ui.settings.SettingsFragment
import com.csci448.kennylieu.kennylieu_A2.ui.welcome.WelcomeFragment

class MainActivity : AppCompatActivity(), WelcomeFragment.Callbacks, SettingsFragment.Callbacks, HistoryFragment.Callbacks, GameFragment.Callbacks {
    private val logTag = "448.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment.newInstance())
                .commitNow()
        }

        //TodO implement Sharedpreferences

        //val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        //val isDarkTheme = prefs.getBoolean("key_dark_mode", false)
    }

    override fun onSettingsClicked(){
        Log.d(logTag, "onSettingsClicked overrided")
        supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment.newInstance()).addToBackStack(null).commit()
    }

    override fun onHistoryClicked(){
        Log.d(logTag, "onHistoryClicked overrided")
        supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment.newInstance()).addToBackStack(null).commit()
    }

    override fun onGameClicked(){
        Log.d(logTag, "onHistoryClicked overrided")
        supportFragmentManager.beginTransaction().replace(R.id.container, GameFragment.newInstance()).addToBackStack(null).commit()
    }

    override fun onSettingsBackClicked() {
        Log.d(logTag, "onSettingsBackClicked()")
    }

    override fun onHistoryBackClicked() {
        Log.d(logTag, "onHistoryBackClicked()")
    }

    override fun onGameBackClicked() {
        Log.d(logTag, "onGameBackClicked()")
        supportFragmentManager.popBackStack()
    }

    enum class DarkModeConfig{
        YES,
        NO,
        FOLLOW_SYSTEM
    }
    //TODO Change enum class to true/false/null
    fun shouldEnableDarkMode(darkModeConfig: DarkModeConfig){
        when(darkModeConfig){
            DarkModeConfig.YES -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            DarkModeConfig.NO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            DarkModeConfig.FOLLOW_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
