package com.csci448.kennylieu.kennylieu_A2.data

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.*
import java.util.concurrent.Executors

class ResultRepository(private val resultDao: ResultDao) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getResults(): LiveData<List<Result>> = resultDao.getResults()

    fun getResult(uid: UUID): LiveData<Result?> = resultDao.getResult(uid)

    fun updateResult(result: Result) {
        executor.execute {
            resultDao.updateResult(result)
        }
    }

    fun addResult(result: Result) {
        executor.execute {
            resultDao.addResult(result)
        }
    }

    fun deleteResult(result: Result) {
        executor.execute {
            resultDao.deleteResult(result)
        }
    }

    fun deleteResults() {
        executor.execute {
            resultDao.deleteResults()
        }
    }

    companion object {
        private var instance: ResultRepository? = null

        fun getInstance(context: Context): ResultRepository? {
            return instance ?: let {
                if(instance==null){
                    val database = ResultDatabase.getInstance(context)
                    instance = ResultRepository(database.resultDao())
                }
                return instance
            }
        }
    }
}