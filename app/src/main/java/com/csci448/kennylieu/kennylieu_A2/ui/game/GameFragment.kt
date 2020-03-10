package com.csci448.kennylieu.kennylieu_A2.ui.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
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
    private var currentPiece: Int = 0

    private var piecesPlaced: Int = 0
    private var gameOver: Boolean = false
    private var playedPieces: MutableList<ImageView> = mutableListOf<ImageView>()
    private var player1Pieces: MutableList<ImageView> = mutableListOf<ImageView>()
    private var player2Pieces: MutableList<ImageView> = mutableListOf<ImageView>()
    private var player1: Boolean = true


    private fun playPiece(block: ImageView){
        if(!playedPieces.contains(block) && !gameOver){
            block.setImageResource(currentPiece)
            if(currentPiece == R.drawable.ic_o)
                currentPiece = R.drawable.ic_x
            else
                currentPiece = R.drawable.ic_o
            playedPieces.add(block)
            piecesPlaced++
            if(player1){
                player1Pieces.add(block)
                player1 = false
            } else {
                player2Pieces.add(block)
                player1 = true
            }
        }
        if(player1Pieces.size>=3 &&
            (player1Pieces.contains(block1) && player1Pieces.contains(block2) && player1Pieces.contains(block3) ||
            player1Pieces.contains(block4) && player1Pieces.contains(block5) && player1Pieces.contains(block6) ||
            player1Pieces.contains(block7) && player1Pieces.contains(block8) && player1Pieces.contains(block9) ||
            player1Pieces.contains(block1) && player1Pieces.contains(block4) && player1Pieces.contains(block7) ||
            player1Pieces.contains(block2) && player1Pieces.contains(block5) && player1Pieces.contains(block8) ||
            player1Pieces.contains(block3) && player1Pieces.contains(block6) && player1Pieces.contains(block9) ||
            player1Pieces.contains(block1) && player1Pieces.contains(block5) && player1Pieces.contains(block9) ||
            player1Pieces.contains(block3) && player1Pieces.contains(block5) && player1Pieces.contains(block7) ) )
        {
            Toast.makeText(activity,"Player 1 Wins!", Toast.LENGTH_SHORT).show()
            gameOver = true
            return
        }
        if(player2Pieces.size>=3 &&
            (player2Pieces.contains(block1) && player2Pieces.contains(block2) && player2Pieces.contains(block3) ||
            player2Pieces.contains(block4) && player2Pieces.contains(block5) && player2Pieces.contains(block6) ||
            player2Pieces.contains(block7) && player2Pieces.contains(block8) && player2Pieces.contains(block9) ||
            player2Pieces.contains(block1) && player2Pieces.contains(block4) && player2Pieces.contains(block7) ||
            player2Pieces.contains(block2) && player2Pieces.contains(block5) && player2Pieces.contains(block8) ||
            player2Pieces.contains(block3) && player2Pieces.contains(block6) && player2Pieces.contains(block9) ||
            player2Pieces.contains(block1) && player2Pieces.contains(block5) && player2Pieces.contains(block9) ||
            player2Pieces.contains(block3) && player2Pieces.contains(block5) && player2Pieces.contains(block7) ) )
        {
            Toast.makeText(activity,"Player 2 Wins!", Toast.LENGTH_SHORT).show()
            gameOver = true
            return
        }
        if(piecesPlaced==9){
            Toast.makeText(activity,"GAME OVER, Tie", Toast.LENGTH_SHORT).show()
            gameOver = true
            return
        }

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
            playedPieces = mutableListOf<ImageView>()
            player1Pieces = mutableListOf<ImageView>()
            player2Pieces = mutableListOf<ImageView>()
            gameOver = false
            piecesPlaced = 0
            player1 = true
            currentPiece = R.drawable.ic_o
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

            if(sharedPref.getBoolean("x_first", false)==false){ //if o goes first
                Log.d(logTag, "getBoolean returned false for x goes first")
                currentPiece = R.drawable.ic_o
            } else {
                Log.d(logTag, "getBoolean returned true for x goes first")
                currentPiece = R.drawable.ic_x //if x goes first
            }
        }
        goBackButton.setOnClickListener {
            callbacks?.onGameBackClicked()
        }

        block1.setOnClickListener {
            playPiece(block1)
        }
        block2.setOnClickListener {
            playPiece(block2)
        }
        block3.setOnClickListener {
            playPiece(block3)
        }
        block4.setOnClickListener {
            playPiece(block4)
        }
        block5.setOnClickListener {
            playPiece(block5)
        }
        block6.setOnClickListener {
            playPiece(block6)
        }
        block7.setOnClickListener {
            playPiece(block7)
        }
        block8.setOnClickListener {
            playPiece(block8)
        }
        block9.setOnClickListener {
            playPiece(block9)
        }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        if(sharedPref.getBoolean("x_first", false)==false){ //if o goes first
            currentPiece = R.drawable.ic_o
        } else {
            currentPiece = R.drawable.ic_x //if x goes first
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
        //callbacks?.onGameBackClicked() //could move to onDetach or onDestroyView
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