package com.example.divartask.presentation.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.TypeConverter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.divartask.data.database.entity.DetailEntity
import com.example.divartask.data.database.entity.PlacesEntity
import com.example.divartask.data.database.entity.WidgetsEntity
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.data.remote.entity.UserLocationData
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.model.WidgetsDomain
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Fragment.flowLife(flow: Flow<T>, collector: FlowCollector<T>) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collector)
        }
    }
}

fun Fragment.showToast(message: String) {
    if (message.isNotEmpty())
        this.view?.let {
            Toast.makeText(
                this.context,
                message, Toast.LENGTH_LONG
            ).show()
        }
}

fun DetailData.convertToDomainModel(): List<DetailDomain> {
    val widgetList: ArrayList<DetailDomain> = arrayListOf()
    val items: ArrayList<String> = arrayListOf()
    widgets?.forEach {
        it.data?.items?.forEach {
            items.add(it?.imageUrl ?: "")
        }
        widgetList.add(
            DetailDomain(
                it.widgetType ?: "",
                items,
                it.data?.subtitle ?: "",
                it.data?.text ?: "",
                it.data?.title ?: "",
                it.data?.value ?: ""
            )
        )
    }
    return widgetList
}

fun ImageView.loadImageToView(url: String) {

    if (url.isNotEmpty()) {

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(false)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
            .into(this)
    }


}

fun List<PlacesEntity>.convertToPlacesDomainModel(): List<PlacesDomain> {
    val list: ArrayList<PlacesDomain> = arrayListOf()
    this.forEach {
        list.add(
            PlacesDomain(it.cityName, it.cityID)
        )
    }
    return list
}

fun List<WidgetsEntity>.convertToWidgetsDomainModel(): List<WidgetsDomain> {

    val list: ArrayList<WidgetsDomain> = arrayListOf()
    this.forEach {
        list.add(
            WidgetsDomain(
                it.cityID,
                it.widgetType,
                it.text,
                it.subTitle,
                it.district,
                it.price,
                it.thumbnail,
                it.title,
                it.token
            )
        )
    }
    return list
}

fun List<DetailEntity>.convertToDetailsDomainModel(): List<DetailDomain> {

    val list: ArrayList<DetailDomain> = arrayListOf()
    this.forEach {
        list.add(
            DetailDomain(
                it.widgetType,
                restoreList(it.items),
                it.subTitle,
                it.text,
                it.title,
                it.value,
            )
        )
    }
    return list
}

fun UserLocationData.convertToLocationDomainModel(): PlacesDomain {

    return PlacesDomain(
        this.name?:"",
        this.id?:0
    )
}

fun Context.putIdInSharedPref(id: Int){
    val sharedPref: SharedPreferences = getSharedPreferences("AppSharedPref", 0)
    val editor = sharedPref.edit()
    editor.putInt("ID", id)
    editor.commit()
}

fun Context.getIdFromSharedPref(): Int{
    val sharedPref: SharedPreferences = getSharedPreferences("AppSharedPref", 0)
    return sharedPref.getInt("ID", 1)
}