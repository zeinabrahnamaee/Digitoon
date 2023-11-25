package com.example.divartask.presentation.util

import androidx.room.TypeConverter
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