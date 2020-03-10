package com.csci448.kennylieu.kennylieu_A2.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.csci448.kennylieu.kennylieu_A2.data.Result
import com.csci448.kennylieu.kennylieu_A2.data.ResultRepository
import java.util.*

class GameViewModel(private val resultRepository: ResultRepository) : ViewModel(){
    private val resultIdLiveData = MutableLiveData<UUID>()

    var resultLiveData: LiveData<Result?> =
        Transformations.switchMap(resultIdLiveData) { resultId ->
            resultRepository.getResult(resultId)
        }

    fun loadResult(resultId: UUID) {
        resultIdLiveData.value = resultId
    }

    fun saveResult(result: Result){
        resultRepository.updateResult(result)
    }

    fun deleteResult(result: Result){
        resultRepository.deleteResult(result)
    }

    fun addResult(result: Result){
        resultRepository.addResult(result)
    }
}