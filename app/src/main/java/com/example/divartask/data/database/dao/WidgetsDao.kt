package com.example.divartask.data.database.dao

import androidx.room.*
import com.example.divartask.data.database.entity.WidgetsEntity


@Dao
interface WidgetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWidgets(widgets: List<WidgetsEntity>)

    @Query("SELECT * FROM WIDGETS_TABLE where city_id=:id")
    fun getWidgets(id: Int): List<WidgetsEntity>

    @Query("SELECT COUNT(id) FROM WIDGETS_TABLE where city_id=:id")
    fun getCount(id: Int): Int
}