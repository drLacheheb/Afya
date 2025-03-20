package com.example.afya.data.repository

import com.example.afya.data.model.Post
import com.example.afya.data.model.PostType
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor() : PostRepository {
    private val _posts = mutableListOf(
        Post(
            id = "1",
            title = "Extra Painkillers",
            content = "I have extra ibuprofen tablets",
            drugName = "Ibuprofen",
            image = "https://images.unsplash.com/photo-1599458252573-56ae36120de1?w=400",
            location = "Algiers",
            postType = PostType.OFFER,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            expiredAt = System.currentTimeMillis() + 86400000
        ),
        Post(
            id = "2",
            title = "Looking for Insulin",
            content = "Urgently need insulin pens",
            drugName = "Insulin",
            image = "https://images.unsplash.com/photo-1615461066841-6116e61058f4?w=400",
            location = "Oran",
            postType = PostType.REQUEST,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            expiredAt = System.currentTimeMillis() + 172800000
        )
    )

    private val _postFlow = MutableStateFlow(_posts.toList())
    override fun getPosts(): Flow<List<Post>> = _postFlow.asStateFlow()

    override suspend fun addPost(post: Post) {
        _posts.add(post)
        _postFlow.value = _posts.toList()
    }
}
