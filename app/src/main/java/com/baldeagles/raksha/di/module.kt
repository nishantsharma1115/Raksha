package com.baldeagles.raksha.di

import android.content.Context
import androidx.core.view.ViewCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baldeagles.raksha.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        DataBase::class.java,
        "SAFE_HOUSE_DATABASE"
    )
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun providesDao(database: DataBase) = database.dao

}