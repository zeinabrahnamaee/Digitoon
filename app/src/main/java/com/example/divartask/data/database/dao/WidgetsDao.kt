package com.example.divartask.data.database.dao

import androidx.room.*
import com.example.divartask.data.database.entity.PlacesEntity


@Dao
interface WidgetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWidgets(places: List<PlacesEntity>)

    @Query("SELECT * FROM WIDGETS_TABLE")
    fun getWidgets(): List<PlacesEntity>

    @Query("SELECT FIRST(id) FROM WIDGETS_TABLE")
    fun getId(): Int
}