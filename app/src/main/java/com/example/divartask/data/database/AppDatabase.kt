package com.example.divartask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.divartask.data.database.dao.PlacesDao
import com.example.divartask.data.database.entity.PlacesEntity

@Database(entities = [PlacesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun placesDao(): PlacesDao

}

