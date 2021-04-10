package com.baldeagles.raksha.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baldeagles.raksha.data.models.SafeHouse

@Database(entities = [SafeHouse::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract val dao:Dao
}