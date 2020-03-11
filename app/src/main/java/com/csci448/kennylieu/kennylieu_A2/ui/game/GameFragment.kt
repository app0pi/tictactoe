package com.csci448.kennylieu.kennylieu_A2.ui.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csci448.kennylieu.kennylieu_A2.R
import com.csci448.kennylieu.kennylieu_A2.data.Result
import com.csci448.kennylieu.kennylieu_A2.ui.history.HistoryAdapter
import com.csci448.kennylieu.kennylieu_A2.ui.history.HistoryViewModel

class GameFragment : Fragment() {
    interface Callbacks {
        fun onGameBackClicked()
        fun onGameClicked()
    }

    private val logTag = "448.GameFragment"

    private var callbacks: Callbacks? = null

    //TODO there are two viewmodels, fix that

    //private lateinit var viewModel: GameViewModel

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

    private var player1Piece: Int = 0
    private var player2Piece: Int = 0

    private var currentPiece: Int = 0
    private var piecesPlaced: Int = 0
    private var player1: Boolean = true
    private var gameOver: Boolean = false
//    private var playedPieces: MutableList<ImageView> = mutableListOf<ImageView>()
//    private var player1Pieces: MutableList<ImageView> = mutableListOf<ImageView>()
//    private var player2Pieces: MutableList<ImageView> = mutableListOf<ImageView>()

    private var playedPieces: ArrayList<Int?>? = ArrayList<Int?>() //FOR PURPOSES OF BUNDLE
    private var player1Pieces: ArrayList<Int?>? = ArrayList<Int?>() //FOR PURPOSES OF BUNDLE
    private var player2Pieces: ArrayList<Int?>? = ArrayList<Int?>() //FOR PURPOSES OF BUNDLE

    //TRUSTED ADDITIONS
    private lateinit var gameViewModel: GameViewModel

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(logTag, "onSaveInstanceState")
        outState.putInt("player1Piece", player1Piece)
        outState.putInt("player2Piece", player2Piece)
        outState.putInt("piecesPlaced", piecesPlaced)
        outState.putInt("currentPiece", currentPiece)
        outState.putBoolean("gameOver", gameOver)
        outState.putBoolean("player1", player1)
        outState.putIntegerArrayList("playedPieces", playedPieces) //FOR PURPOSES OF BUNDLE
        outState.putIntegerArrayList("player1Pieces", player1Pieces) //FOR PURPOSES OF BUNDLE
        outState.putIntegerArrayList("player2Pieces", player2Pieces) //FOR PURPOSES OF BUNDLE
    }

    private fun playPiece(block: ImageView){
//        if(!playedPieces.contains(block) && !gameOver){
        if(playedPieces?.contains(block.id)==false && !gameOver){ //FOR PURPOSES OF BUNDLE
            block.setImageResource(currentPiece)
            if(currentPiece == R.drawable.ic_o)
                currentPiece = R.drawable.ic_x
            else
                currentPiece = R.drawable.ic_o
            //playedPieces.add(block)
            playedPieces?.add(block.id) //FOR PURPOSES OF BUNDLE
            piecesPlaced++
            if(player1){
                //player1Pieces.add(block)
                player1Pieces?.add(block.id)//FOR PURPOSES OF BUNDLE
                player1 = false
            } else {
                //player2Pieces.add(block)
                player2Pieces?.add(block.id) //FOR PURPOSES OF BUNDLE
                player1 = true
            }
            /*if(player1Pieces.size>=3 &&
                (player1Pieces.contains(block1) && player1Pieces.contains(block2) && player1Pieces.contains(block3) ||
                        player1Pieces.contains(block4) && player1Pieces.contains(block5) && player1Pieces.contains(block6) ||
                        player1Pieces.contains(block7) && player1Pieces.contains(block8) && player1Pieces.contains(block9) ||
                        player1Pieces.contains(block1) && player1Pieces.contains(block4) && player1Pieces.contains(block7) ||
                        player1Pieces.contains(block2) && player1Pieces.contains(block5) && player1Pieces.contains(block8) ||
                        player1Pieces.contains(block3) && player1Pieces.contains(block6) && player1Pieces.contains(block9) ||
                        player1Pieces.contains(block1) && player1Pieces.contains(block5) && player1Pieces.contains(block9) ||
                        player1Pieces.contains(block3) && player1Pieces.contains(block5) && player1Pieces.contains(block7) ) )*/
            if( (player1Pieces?.size==3 || player1Pieces?.size==4 || player1Pieces?.size==5)  &&
                (player1Pieces?.contains(block1.id)==true && player1Pieces?.contains(block2.id)==true && player1Pieces?.contains(block3.id)==true ||
                        player1Pieces?.contains(block4.id)==true && player1Pieces?.contains(block5.id)==true && player1Pieces?.contains(block6.id)==true ||
                        player1Pieces?.contains(block7.id)==true && player1Pieces?.contains(block8.id)==true && player1Pieces?.contains(block9.id)==true ||
                        player1Pieces?.contains(block1.id)==true && player1Pieces?.contains(block4.id)==true && player1Pieces?.contains(block7.id)==true ||
                        player1Pieces?.contains(block2.id)==true && player1Pieces?.contains(block5.id)==true && player1Pieces?.contains(block8.id)==true ||
                        player1Pieces?.contains(block3.id)==true && player1Pieces?.contains(block6.id)==true && player1Pieces?.contains(block9.id)==true ||
                        player1Pieces?.contains(block1.id)==true && player1Pieces?.contains(block5.id)==true && player1Pieces?.contains(block9.id)==true ||
                        player1Pieces?.contains(block3.id)==true && player1Pieces?.contains(block5.id)==true && player1Pieces?.contains(block7.id)==true ) ) //FOR PURPOSES OF BUNDLE
            {
                Toast.makeText(activity,"Player 1 Wins!", Toast.LENGTH_SHORT).show()
                gameOver = true
                val result = Result()
                result.winner = "Winner: Player 1.  "
                if(currentPiece==R.drawable.ic_o){
                    result.winner += "Winning Piece: X"
                } else {
                    result.winner += "Winning Piece: O"
                }
                gameViewModel.addResult(result)
                return
            }
            /*if(player2Pieces.size>=3 &&
                (player2Pieces.contains(block1) && player2Pieces.contains(block2) && player2Pieces.contains(block3) ||
                        player2Pieces.contains(block4) && player2Pieces.contains(block5) && player2Pieces.contains(block6) ||
                        player2Pieces.contains(block7) && player2Pieces.contains(block8) && player2Pieces.contains(block9) ||
                        player2Pieces.contains(block1) && player2Pieces.contains(block4) && player2Pieces.contains(block7) ||
                        player2Pieces.contains(block2) && player2Pieces.contains(block5) && player2Pieces.contains(block8) ||
                        player2Pieces.contains(block3) && player2Pieces.contains(block6) && player2Pieces.contains(block9) ||
                        player2Pieces.contains(block1) && player2Pieces.contains(block5) && player2Pieces.contains(block9) ||
                        player2Pieces.contains(block3) && player2Pieces.contains(block5) && player2Pieces.contains(block7) ) )*/
            if( (player2Pieces?.size==3 || player2Pieces?.size==4 || player2Pieces?.size==5) &&
                (player2Pieces?.contains(block1.id)==true && player2Pieces?.contains(block2.id)==true && player2Pieces?.contains(block3.id)==true ||
                        player2Pieces?.contains(block4.id)==true && player2Pieces?.contains(block5.id)==true && player2Pieces?.contains(block6.id)==true ||
                        player2Pieces?.contains(block7.id)==true && player2Pieces?.contains(block8.id)==true && player2Pieces?.contains(block9.id)==true ||
                        player2Pieces?.contains(block1.id)==true && player2Pieces?.contains(block4.id)==true && player2Pieces?.contains(block7.id)==true ||
                        player2Pieces?.contains(block2.id)==true && player2Pieces?.contains(block5.id)==true && player2Pieces?.contains(block8.id)==true ||
                        player2Pieces?.contains(block3.id)==true && player2Pieces?.contains(block6.id)==true && player2Pieces?.contains(block9.id)==true ||
                        player2Pieces?.contains(block1.id)==true && player2Pieces?.contains(block5.id)==true && player2Pieces?.contains(block9.id)==true ||
                        player2Pieces?.contains(block3.id)==true && player2Pieces?.contains(block5.id)==true && player2Pieces?.contains(block7.id)==true ) ) //FOR PURPOSES OF BUNDLE
            {
                Toast.makeText(activity,"Player 2 Wins!", Toast.LENGTH_SHORT).show()
                gameOver = true
                val result = Result()
                result.winner = "Winner: Player 2.  "
                if(currentPiece==R.drawable.ic_o){
                    result.winner += "Winning Piece: X"
                } else {
                    result.winner += "Winning Piece: O"
                }
                gameViewModel.addResult(result)
                return
            }
            if(piecesPlaced==9){
                Toast.makeText(activity,"GAME OVER, Tie", Toast.LENGTH_SHORT).show()
                gameOver = true
                val result = Result()
                result.winner = "Tie.  Winning Piece: None"
                gameViewModel.addResult(result)
                return
            }
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
//            playedPieces = mutableListOf<ImageView>()
//            player1Pieces = mutableListOf<ImageView>()
//            player2Pieces = mutableListOf<ImageView>()
            playedPieces = ArrayList<Int?>()
            player1Pieces = ArrayList<Int?>()
            player2Pieces = ArrayList<Int?>()
            gameOver = false
            piecesPlaced = 0
            player1 = true
            currentPiece = R.drawable.ic_o
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

            if(sharedPref.getBoolean("x_first", false)==false){ //if o goes first
                Log.d(logTag, "getBoolean returned false for x goes first")
                player1Piece = R.drawable.ic_o
                player2Piece = R.drawable.ic_x
                currentPiece = R.drawable.ic_o
            } else {//if x goes first
                Log.d(logTag, "getBoolean returned true for x goes first")
                player1Piece = R.drawable.ic_x
                player2Piece = R.drawable.ic_o
                currentPiece = R.drawable.ic_x
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

        for(blockId in playedPieces.orEmpty()){
            if(blockId == R.id.block1) {
                if(player1Pieces?.contains(blockId)==true){ block1.setImageResource(player1Piece) } else { block1.setImageResource(player2Piece) }
            }

        }

        return view
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")

        //TRUSTED ADDITIONS
        val factory = GameViewModelFactory(requireContext())
        gameViewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)

        player1Piece = savedInstanceState?.getInt("player1Piece", player1Piece) ?: 0
        player2Piece = savedInstanceState?.getInt("player2Piece", player2Piece) ?: 0
        piecesPlaced = savedInstanceState?.getInt("piecesPlaced", piecesPlaced) ?: 0
        currentPiece = savedInstanceState?.getInt("currentPiece", currentPiece) ?: 0
        gameOver = savedInstanceState?.getBoolean("gameOver", gameOver) ?: false
        player1 = savedInstanceState?.getBoolean("player1", player1) ?: true

        var blep: String? = "playedPieces"
        val clep: String? = "player1Pieces"
        val dlep: String? = "player2Pieces"
        playedPieces = savedInstanceState?.getIntegerArrayList(blep)
        player1Pieces = savedInstanceState?.getIntegerArrayList(clep)
        player2Pieces = savedInstanceState?.getIntegerArrayList(dlep)
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

}