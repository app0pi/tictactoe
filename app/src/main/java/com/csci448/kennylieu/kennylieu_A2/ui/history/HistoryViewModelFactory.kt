package com.csci448.kennylieu.kennylieu_A2.ui.history


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.kennylieu.kennylieu_A2.data.ResultRepository

class HistoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ResultRepository::class.java).newInstance(ResultRepository.getInstance(context))
    }
}