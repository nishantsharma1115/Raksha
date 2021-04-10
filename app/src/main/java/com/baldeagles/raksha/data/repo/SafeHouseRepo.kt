package com.baldeagles.raksha.data.repo

import androidx.lifecycle.LiveData
import com.baldeagles.raksha.data.models.SafeHouse

interface SafeHouseRepo {

    suspend fun insertSafeHouse(safeHouse: SafeHouse)

    suspend fun deleteSafeHouse(safeHouse: SafeHouse)

    fun getAllSafeHouse(): LiveData<List<SafeHouse>>
}