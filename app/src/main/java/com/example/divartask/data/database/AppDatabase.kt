package com.example.divartask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.divartask.data.database.dao.PlacesDao
import com.example.divartask.data.database.dao.WidgetsDao
import com.example.divartask.data.database.entity.PlacesEntity
import com.example.divartask.data.database.entity.WidgetsEntity

@Database(entities = [PlacesEntity::class, WidgetsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun placesDao(): PlacesDao
    abstract fun widgetsDao(): WidgetsDao

}

