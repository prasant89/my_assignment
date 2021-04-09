package com.assignment.infosys.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Row(
    @SerialName("description")
    val description: String,
    @SerialName("imageHref")
    val imageHref: String,
    @SerialName("title")
    val title: String
)