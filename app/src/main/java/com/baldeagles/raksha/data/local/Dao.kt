package com.baldeagles.raksha.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.baldeagles.raksha.data.models.SafeHouse


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSafeHouse(safeHouse: SafeHouse)

    @Delete
    suspend fun deleteSafeHouse(safeHouse: SafeHouse)

    @Query("SELECT * FROM SafeHouse ORDER BY timeStamp DESC")
    fun getAllSafeHouses(): LiveData<List<SafeHouse>>
}