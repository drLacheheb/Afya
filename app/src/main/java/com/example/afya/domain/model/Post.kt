package com.example.afya.domain.model

import com.example.afya.data.model.Post as DataPost


data class Post(
    val title: String,
    val drugName: String,
    val content: String,
    val location: String,
    val imageUrl: String? = null,
    val postType: PostType
) {
    fun toDataModel(): DataPost {
        return DataPost(
            id = "",
            title = title,
            drugName = drugName,
            content = content,
            location = location,
            image = imageUrl ?: "",
            postType = postType.toDataModel(),
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            expiredAt = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)
        )
    }
}
