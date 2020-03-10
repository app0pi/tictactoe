package com.csci448.kennylieu.kennylieu_A2.ui.game

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.csci448.kennylieu.kennylieu_A2.R

class GameFragment : Fragment() {
    interface Callbacks {
        fun onGameBackClicked()
        fun onGameClicked()
    }

    private val logTag = "448.GameFragment"

    private var callbacks: Callbacks? = null

    private lateinit var viewModel: GameViewModel

    private lateinit var playAgainButton: Button
    private lateinit var goBackButton: Button
    private lateinit var block1: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(logTag, "onCreateView() called")
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.game_fragment, container, false)
        playAgainButton = view?.findViewById(R.id.play_again_button) as Button
        goBackButton = view?.findViewById(R.id.go_back_button) as Button
        playAgainButton.setOnClickListener {
            //TODO set play again button action
        }
        goBackButton.setOnClickListener {
            callbacks?.onGameBackClicked()
        }
        block1 = view?.findViewById(R.id.block1) as ImageView
        block1.setOnClickListener {
            block1.setImageResource(R.drawable.ic_o)
            //block1.setImageResource(android.R.color.transparent)
        }
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")

    }


    companion object {
        fun newInstance() = GameFragment()
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
    }
    override fun onDestroy(){
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
        callbacks?.onGameBackClicked() //could move to onDetach or onDestroyView
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
    /*
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) { //take out other question mark?
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.welcome_fragment_menu, menu)
    }
    */
}