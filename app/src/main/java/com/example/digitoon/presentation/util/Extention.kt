package com.example.digitoon.presentation.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.digitoon.R
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

fun ImageView.loadImageToView(url: String) {

    if (url.isNotEmpty()) {

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.digi_logo)
            .skipMemoryCache(false)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
            .into(this)
    }


}

fun Context.putIdInSharedPref(id: String){
    val sharedPref: SharedPreferences = getSharedPreferences("AppSharedPref", 0)
    val editor = sharedPref.edit()
    editor.putString("ID", id)
    editor.commit()
}

fun Context.getIdFromSharedPref(): String{
    val sharedPref: SharedPreferences = getSharedPreferences("AppSharedPref", 0)
    return sharedPref.getString("ID", "")?:""
}