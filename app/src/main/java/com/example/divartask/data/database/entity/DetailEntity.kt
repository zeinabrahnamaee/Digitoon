package com.example.divartask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DETAIL_TABLE")
data class DetailEntity(
    @ColumnInfo(name = "Widget_Type")
    var widgetType: String,
    @ColumnInfo(name = "Items" )
    var items: String,
    @ColumnInfo(name = "Sub_Title" )
    var subTitle: String,
    @ColumnInfo(name = "Text" )
    var text: String,
    @ColumnInfo(name = "Value" )
    var value: String,
    @ColumnInfo(name = "Title" )
    var title: String,
    @ColumnInfo(name = "Token" )
    var token: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}