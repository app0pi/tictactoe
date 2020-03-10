package com.csci448.kennylieu.kennylieu_A2.ui.history

import androidx.lifecycle.ViewModel
import com.csci448.kennylieu.kennylieu_A2.data.ResultRepository
import com.csci448.kennylieu.kennylieu_A2.data.Result

class HistoryViewModel(private val resultRepository: ResultRepository) : ViewModel(){
    val resultLiveListData = resultRepository.getResults()

    fun addResult(result: Result){
        resultRepository.addResult(result)
    }
}