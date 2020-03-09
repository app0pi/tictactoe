package com.csci448.kennylieu.kennylieu_A2.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.csci448.kennylieu.kennylieu_A2.R

class WelcomeFragment : Fragment() {
    interface Callbacks {
        fun onSettingsClicked()
        fun onHistoryClicked()
        fun onGameClicked()
    }

    private val logTag = "448.WelcomeFragment"

    private var callbacks: Callbacks? = null

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onAttach(context: Context) {
        Log.d(tag, "onAttach() called")
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(logTag, "onCreateView() called")
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { //take out other question mark?; ok I took it out
        Log.d(logTag, "onCreateOptionsMenu() called")
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.welcome_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.settings_menu_item -> {
                Log.d(logTag, "settings clicked")
                callbacks?.onSettingsClicked()
                true
            }
            R.id.history_menu_item -> {
                Log.d(logTag, "history clicked")
                callbacks?.onHistoryClicked()
                true
            }
            R.id.game_menu_item -> {
                Log.d(logTag, "play game clicked")
                callbacks?.onGameClicked()
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

    override fun onDetach() {
        Log.d(tag, "onAttach() called")
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
    }
    override fun onDestroy(){
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
    override fun onDestroyView(){
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }
}
