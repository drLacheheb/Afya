package com.example.afya.data.model

data class Post(
    val id: String,
    val title: String,
    val drugName: String,
    val content: String,
    val location: String,
    val image: String,
    val postType: PostType,  // الآن يستخدم PostType من data.model فقط
    val createdAt: Long,
    val updatedAt: Long,
    val expiredAt: Long
)


