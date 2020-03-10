package com.csci448.kennylieu.kennylieu_A2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface ResultDao {
    @Query("SELECT * FROM result")
    fun getResults(): LiveData<List<Result>>

    @Query("SELECT * FROM result WHERE uid=(:uid)")
    fun getResult(uid: UUID): LiveData<Result?>

    @Update
    fun updateResult(result: Result)

    @Insert
    fun addResult(result: Result)

    @Delete
    fun deleteResult(result: Result)
}