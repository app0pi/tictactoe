package com.csci448.kennylieu.kennylieu_A2.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csci448.kennylieu.kennylieu_A2.R
import com.csci448.kennylieu.kennylieu_A2.data.Result

class HistoryAdapter(private val results: List<Result>, private val clickListener: (Result) -> Unit) : RecyclerView.Adapter<ResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_result, parent, false)
        return ResultHolder(view)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val crime = results[position]
        holder.bind(crime, clickListener)
    }
}