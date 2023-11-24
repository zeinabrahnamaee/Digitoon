package com.example.divartask.presentation.util

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.divartask.data.database.entity.PlacesEntity
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.domain.model.PlacesDomain
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

fun Fragment.showToast( message: String) {
    if (message.isNotEmpty())
        this.view?.let {
            Toast.makeText(
                this.context,
                message
            , Toast.LENGTH_LONG).show()
        }
}

fun DetailData.convertToDomainModel(): DetailDomain{
    val widgetList: ArrayList< DetailDomain.Widget> = arrayListOf()
    val items: ArrayList<String> = arrayListOf()
    widgets?.forEach {
        it.data?.items?.forEach {
            items.add(it?.imageUrl?:"")
        }
        widgetList.add(
            DetailDomain.Widget(
                DetailDomain.Widget.Data(
                    it.data?.imageUrl?: "",
                    items,
                    it.data?.showThumbnail?: false,
                    it.data?.subtitle?: "",
                    it.data?.text?: "",
                    it.data?.title?: "",
                    it.data?.value?: ""
                ),
                it.widgetType?:""
            )
        )
    }
    return DetailDomain(
        contactButtonText?: "",
        enableContact?: false,
        widgetList
    )
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
fun List<PlacesEntity>.convertToPlacesDomainModel(): List<PlacesDomain>{
    val list: ArrayList<PlacesDomain> = arrayListOf()
    this.forEach {
        list.add(
            PlacesDomain(it.cityName, it.cityID)
        )
    }
    return list
}