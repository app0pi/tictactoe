package com.csci448.kennylieu.kennylieu_A2.ui.history

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.csci448.kennylieu.kennylieu_A2.R

class HistoryFragment : Fragment() {
    interface Callbacks {
        fun onGameClicked()
        fun onSettingsClicked()
        fun onHistoryBackClicked()
    }

    private val logTag = "448.HistoryFragment"

    private var callbacks: Callbacks? = null

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(logTag, "onCreateView() called")
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")
    }

    companion object {
        fun newInstance() = HistoryFragment()
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
        callbacks?.onHistoryBackClicked()
    }
    override fun onDestroy(){
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
    override fun onDestroyView(){
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(logTag, "onCreateOptionsMenu() called")
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.game_menu_item -> {
                Log.d(logTag, "game clicked")
                callbacks?.onGameClicked()
                true
            }
            R.id.settings_menu_item -> {
                Log.d(logTag, "settings clicked")
                callbacks?.onSettingsClicked()
                true
            }
            R.id.exit_menu_item -> {
                Log.d(logTag, "exit clicked")
                System.exit(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}