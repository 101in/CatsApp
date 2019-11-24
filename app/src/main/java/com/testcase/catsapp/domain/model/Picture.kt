package com.testcase.catsapp.domain.model

data class Picture(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    var isFavourite: Boolean
)