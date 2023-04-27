package com.example.minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao():HistoryDAO
    companion object {
        @Volatile
        private  var INSTANCE: HistoryDatabase? = null;
        fun getInstance(context: Context): HistoryDatabase? {
            var instance : HistoryDatabase? = INSTANCE;
            synchronized(this) {
                instance = Room.databaseBuilder(context.applicationContext, HistoryDatabase::class.java, "history-database").fallbackToDestructiveMigration().build();
                INSTANCE = instance;
            }
            return instance;
        }

    }

}