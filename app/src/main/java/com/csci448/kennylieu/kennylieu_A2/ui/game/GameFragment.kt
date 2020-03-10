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
    private lateinit var block2: ImageView
    private lateinit var block3: ImageView
    private lateinit var block4: ImageView
    private lateinit var block5: ImageView
    private lateinit var block6: ImageView
    private lateinit var block7: ImageView
    private lateinit var block8: ImageView
    private lateinit var block9: ImageView


    private var currentPiece: Int = R.drawable.ic_o

    fun switchPiece(){
        if(currentPiece == R.drawable.ic_o)
            currentPiece = R.drawable.ic_x
        else
            currentPiece = R.drawable.ic_o
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(logTag, "onCreateView() called")
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.game_fragment, container, false)
        playAgainButton = view?.findViewById(R.id.play_again_button) as Button
        goBackButton = view?.findViewById(R.id.go_back_button) as Button
        block1 = view?.findViewById(R.id.block1) as ImageView
        block2 = view?.findViewById(R.id.block2) as ImageView
        block3 = view?.findViewById(R.id.block3) as ImageView
        block4 = view?.findViewById(R.id.block4) as ImageView
        block5 = view?.findViewById(R.id.block5) as ImageView
        block6 = view?.findViewById(R.id.block6) as ImageView
        block7 = view?.findViewById(R.id.block7) as ImageView
        block8 = view?.findViewById(R.id.block8) as ImageView
        block9 = view?.findViewById(R.id.block9) as ImageView

        playAgainButton.setOnClickListener {
            block1.setImageResource(android.R.color.transparent)
            block2.setImageResource(android.R.color.transparent)
            block3.setImageResource(android.R.color.transparent)
            block4.setImageResource(android.R.color.transparent)
            block5.setImageResource(android.R.color.transparent)
            block6.setImageResource(android.R.color.transparent)
            block7.setImageResource(android.R.color.transparent)
            block8.setImageResource(android.R.color.transparent)
            block9.setImageResource(android.R.color.transparent)
        }
        goBackButton.setOnClickListener {
            callbacks?.onGameBackClicked()
        }

        block1.setOnClickListener {
            block1.setImageResource(currentPiece)
            switchPiece()
        }
        block2.setOnClickListener {
            block2.setImageResource(currentPiece)
            switchPiece()
        }
        block3.setOnClickListener {
            block3.setImageResource(currentPiece)
            switchPiece()
        }
        block4.setOnClickListener {
            block4.setImageResource(currentPiece)
            switchPiece()
        }
        block5.setOnClickListener {
            block5.setImageResource(currentPiece)
            switchPiece()
        }
        block6.setOnClickListener {
            block6.setImageResource(currentPiece)
            switchPiece()
        }
        block7.setOnClickListener {
            block7.setImageResource(currentPiece)
            switchPiece()
        }
        block8.setOnClickListener {
            block8.setImageResource(currentPiece)
            switchPiece()
        }
        block9.setOnClickListener {
            block9.setImageResource(currentPiece)
            switchPiece()
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