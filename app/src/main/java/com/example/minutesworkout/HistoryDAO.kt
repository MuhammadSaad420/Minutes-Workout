package com.example.minutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun getAllHistoryData(): Flow<List<HistoryEntity>>
}