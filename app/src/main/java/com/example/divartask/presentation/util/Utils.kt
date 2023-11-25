package com.example.divartask.presentation.util

import android.content.SharedPreferences
import androidx.room.TypeConverter
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@TypeConverter
fun restoreList(listOfString: String?): List<String> {
    return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
}

@TypeConverter
fun saveList(listOfString: List<String?>?): String {
    return Gson().toJson(listOfString)
}

object LocationRequestCreator {
    private const val INTERVAL = 60 * 1000L
    fun create(): LocationRequest {
        return LocationRequest.create().apply {
            interval = INTERVAL
            fastestInterval = 10
            priority = PRIORITY_HIGH_ACCURACY
            maxWaitTime = 60
        }
    }
}