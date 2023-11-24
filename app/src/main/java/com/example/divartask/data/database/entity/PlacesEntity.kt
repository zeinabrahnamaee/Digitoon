package com.example.divartask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PLACES_TABLE")
data class PlacesEntity(
    @ColumnInfo(name = "CITY_NAME")
    var cityName: String,
    @ColumnInfo(name = "CITY_ID" )
    var cityID: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
