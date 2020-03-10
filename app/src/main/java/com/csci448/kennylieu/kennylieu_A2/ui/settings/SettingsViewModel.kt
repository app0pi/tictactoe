package com.csci448.kennylieu.kennylieu_A2.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.csci448.kennylieu.kennylieu_A2.data.Result
import com.csci448.kennylieu.kennylieu_A2.data.ResultRepository
import java.util.*

class SettingsViewModel(private val resultRepository: ResultRepository) : ViewModel(){
    private val resultIdLiveData = MutableLiveData<UUID>()

    fun deleteResults(){
        resultRepository.deleteResults()
    }
}