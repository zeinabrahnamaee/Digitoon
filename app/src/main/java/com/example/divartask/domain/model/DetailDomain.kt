package com.example.divartask.domain.model

data class DetailDomain(
    val widgetType: String,
    val items: List<String>,
    val subtitle: String,
    val text: String,
    val title: String,
    val value: String
)