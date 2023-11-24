package com.example.divartask.data.database

import androidx.room.*


@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(places: List<PlacesEntity>)

    @Query("SELECT * FROM PLACES_TABLE")
    fun getCities(): List<PlacesEntity>

    @Query("SELECT COUNT(id) FROM PLACES_TABLE")
    fun getCount(): Int
}