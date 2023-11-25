package com.example.divartask.data.database.dao

import androidx.room.*
import com.example.divartask.data.database.entity.DetailEntity


@Dao
interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailData(places: List<DetailEntity>)

    @Query("SELECT * FROM DETAIL_TABLE where token=:token")
    fun getDetail(token: String): List<DetailEntity>
}