package com.example.divartask.domain.model

data class DetailDomain(
    val contactButtonText: String,
    val enableContact: Boolean,
    val widgets: List<Widget>
) {
    data class Widget(
        val data: Data,
        val widgetType: String
    ) {
        data class Data(
            val imageUrl: String,
            val items: List<String>,
            val showThumbnail: Boolean,
            val subtitle: String,
            val text: String,
            val title: String,
            val value: String
        )
    }
}