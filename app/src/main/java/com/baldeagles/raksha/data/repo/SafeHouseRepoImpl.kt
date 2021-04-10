package com.baldeagles.raksha.data.repo

import com.baldeagles.raksha.data.local.Dao
import com.baldeagles.raksha.data.models.SafeHouse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SafeHouseRepoImpl @Inject constructor(private val dao: Dao) : SafeHouseRepo {

    override suspend fun insertSafeHouse(safeHouse: SafeHouse) = withContext(Dispatchers.IO) {
        dao.insertSafeHouse(safeHouse)
    }

    override suspend fun deleteSafeHouse(safeHouse: SafeHouse) = withContext(Dispatchers.IO) {
        dao.deleteSafeHouse(safeHouse)
    }

    override fun getAllSafeHouse() = dao.getAllSafeHouses()
}