package com.example.divartask.data.remote.entity


import com.google.gson.annotations.SerializedName

data class DetailData(
    @SerializedName("contact_button_text")
    val contactButtonText: String? = null,
    @SerializedName("enable_contact")
    val enableContact: Boolean? = null,
    @SerializedName("widgets")
    val widgets: List<Widget>? = null
) {
    data class Widget(
        @SerializedName("data")
        val `data`: Data? = null,
        @SerializedName("widget_type")
        val widgetType: String? = null
    ) {
        data class Data(
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("items")
            val items: List<Item?>? = null,
            @SerializedName("show_thumbnail")
            val showThumbnail: Boolean? = null,
            @SerializedName("subtitle")
            val subtitle: String? = null,
            @SerializedName("text")
            val text: String? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("value")
            val value: String? = null
        ) {
            data class Item(
                @SerializedName("image_url")
                val imageUrl: String? = null
            )
        }
    }
}