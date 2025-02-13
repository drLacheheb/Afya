package com.example.afya.model

import java.util.Date

data class Post(
    val id: String,
    val title: String,
    val content: String,
    val drugName: String,
    val image: String?,
    val location: String,
    val postType: PostType,
    val createdAt: Date,
    val updatedAt: Date,
    val expiredAt: Date
)

enum class PostType {
    OFFER,
    REQUEST
}
