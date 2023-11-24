package com.example.divartask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WIDGETS_TABLE")
data class WidgetsEntity(
    @ColumnInfo(name = "CITY_ID" )
    var cityID: Int,
    @ColumnInfo(name = "WIDGET_TYPE" )
    var widgetType: String,
    @ColumnInfo(name = "TEXT" )
    var text: Int,
    @ColumnInfo(name = "SUBTITLE" )
    var subTitle: String,
    @ColumnInfo(name = "DISTRICT" )
    var district: String,
    @ColumnInfo(name = "PRICE" )
    var price: String,
    @ColumnInfo(name = "THUMBNAIL" )
    var thumbnail: String,
    @ColumnInfo(name = "TITLE" )
    var title: String,
    @ColumnInfo(name = "TOKEN" )
    var token: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
