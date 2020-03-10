package com.csci448.kennylieu.kennylieu_A2.ui.history

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csci448.kennylieu.kennylieu_A2.R
import com.csci448.kennylieu.kennylieu_A2.data.Result

class ResultHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var result: Result

    val winnerTextView: TextView = itemView.findViewById(R.id.result_winner)
    val dateTextView: TextView = itemView.findViewById(R.id.result_date)
    //private val solvedImageView: ImageView = itemView.findViewById(R.id.result_piece_image)

    fun bind(result: Result, clickListener: (Result) -> Unit) {
        this.result = result
        itemView.setOnClickListener{ clickListener(this.result) }
        winnerTextView.text = this.result.winner
        dateTextView.text = this.result.time.toString()
    }
}