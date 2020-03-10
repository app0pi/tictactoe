package com.csci448.kennylieu.kennylieu_A2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Result(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    var winner: String = "",
    var piece: String = "",
    var time: Date = Date()
)