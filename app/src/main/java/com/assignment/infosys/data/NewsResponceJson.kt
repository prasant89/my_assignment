package com.assignment.infosys.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponceJson(
    @SerialName("rows")
    val rows: List<Row>,
    @SerialName("title")
    val title: String
)