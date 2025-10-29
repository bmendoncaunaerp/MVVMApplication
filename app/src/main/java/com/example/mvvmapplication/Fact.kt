package com.example.mvvmapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    @SerialName("text") val text: String
)
